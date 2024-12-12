package com.example.tricount_clone.service;

import com.example.tricount_clone.domain.Balance;
import com.example.tricount_clone.domain.Expense;
import com.example.tricount_clone.dto.BalanceResponse;
import com.example.tricount_clone.repository.ExpenseRepository;
import com.example.tricount_clone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BalanceService {
	private final ExpenseRepository expenseRepository;
	private final UserRepository userRepository;

	public BalanceService(ExpenseRepository expenseRepository, UserRepository userRepository) {
		this.expenseRepository = expenseRepository;
		this.userRepository = userRepository;
	}

	public List<BalanceResponse> calculateBalances(Long settlementId) {
		// 특정 정산의 지출 목록 조회
		List<Expense> expenses = expenseRepository.findBySettlementId(settlementId);

		// 사용자별 총 지출 계산
		Map<Long, BigDecimal> userExpenses = new HashMap<>();
		for (Expense expense : expenses) {
			userExpenses.put(
				expense.getUserId(),
				userExpenses.getOrDefault(expense.getUserId(), BigDecimal.ZERO).add(expense.getAmount())
			);
		}

		// 정산 참여자 목록 및 총 지출 금액
		Set<Long> userIds = userExpenses.keySet();
		BigDecimal totalAmount = userExpenses.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal perUserAmount = totalAmount.divide(BigDecimal.valueOf(userIds.size()), 2, BigDecimal.ROUND_HALF_UP);

		// 정산 금액 계산
		Map<Long, BigDecimal> balances = new HashMap<>();
		for (Long userId : userIds) {
			balances.put(userId, userExpenses.get(userId).subtract(perUserAmount));
		}

		// 송금 목록 생성
		List<Balance> results = settleBalances(balances);

		// Balance 엔티티를 BalanceResponse로 변환
		return results.stream()
			.map(b -> new BalanceResponse(
				b.getSenderUserId(),
				b.getSenderUserName(),
				b.getSendAmount(),
				b.getReceiverUserId(),
				b.getReceiverUserName()
			))
			.collect(Collectors.toList());
	}

	private List<Balance> settleBalances(Map<Long, BigDecimal> balances) {
		List<Balance> results = new ArrayList<>();

		// 부채가 있는 사용자와 초과 지출 사용자 목록
		List<Map.Entry<Long, BigDecimal>> creditors = balances.entrySet().stream()
			.filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) > 0)
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toList());

		List<Map.Entry<Long, BigDecimal>> debtors = balances.entrySet().stream()
			.filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) < 0)
			.sorted((a, b) -> b.getValue().compareTo(a.getValue()))
			.collect(Collectors.toList());

		int i = 0, j = 0;
		while (i < creditors.size() && j < debtors.size()) {
			Map.Entry<Long, BigDecimal> creditor = creditors.get(i);
			Map.Entry<Long, BigDecimal> debtor = debtors.get(j);

			BigDecimal transferAmount = creditor.getValue().min(debtor.getValue().negate());

			String senderName = userRepository.getUserNameById(debtor.getKey());
			String receiverName = userRepository.getUserNameById(creditor.getKey());

			// Null 체크
			if (senderName == null || receiverName == null) {
				throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다.");
			}

			results.add(new Balance(
				debtor.getKey(),
				senderName,
				transferAmount,
				creditor.getKey(),
				receiverName
			));

			balances.put(creditor.getKey(), creditor.getValue().subtract(transferAmount));
			balances.put(debtor.getKey(), debtor.getValue().add(transferAmount));

			if (balances.get(creditor.getKey()).compareTo(BigDecimal.ZERO) == 0) i++;
			if (balances.get(debtor.getKey()).compareTo(BigDecimal.ZERO) == 0) j++;
		}

		return results;
	}
}
