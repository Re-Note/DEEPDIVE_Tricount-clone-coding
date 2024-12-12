package com.example.tricount_clone.service;

import com.example.tricount_clone.domain.Expense;
import com.example.tricount_clone.dto.ExpenseRequest;
import com.example.tricount_clone.dto.ExpenseResponse;
import com.example.tricount_clone.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public void createExpense(ExpenseRequest request) {
		Expense expense = Expense.builder()
			.name(request.getName())
			.amount(request.getAmount())
			.date(request.getDate())
			.settlementId(request.getSettlementId())
			.userId(request.getUserId())
			.build();
		expenseRepository.save(expense);
	}

	public List<ExpenseResponse> getExpensesBySettlementId(Long settlementId) {
		return expenseRepository.findBySettlementId(settlementId).stream()
			.map(e -> new ExpenseResponse(
				e.getId(),
				e.getName(),
				e.getAmount(),
				e.getDate(),
				e.getSettlementId(),
				e.getUserId()))
			.collect(Collectors.toList());
	}

	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}

}
