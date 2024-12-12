package com.example.tricount_clone.service;

import com.example.tricount_clone.domain.Settlement;
import com.example.tricount_clone.dto.SettlementRequest;
import com.example.tricount_clone.dto.SettlementResponse;
import com.example.tricount_clone.repository.SettlementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettlementService {
	private final SettlementRepository settlementRepository;

	public SettlementService(SettlementRepository settlementRepository) {
		this.settlementRepository = settlementRepository;
	}

	public void createSettlement(SettlementRequest request) {
		Settlement settlement = Settlement.builder()
			.name(request.getName())
			.ownerId(request.getOwnerId())
			.build();
		settlementRepository.save(settlement);
	}

	public Long createSettlementAndReturnId(SettlementRequest request) {
		Settlement settlement = Settlement.builder()
			.name(request.getName())
			.ownerId(request.getOwnerId())
			.build();
		Long settlementId = settlementRepository.saveAndReturnId(settlement); // 저장 후 ID 반환
		return settlementId;
	}

	public List<SettlementResponse> getAllSettlements() {
		return settlementRepository.findAll().stream()
			.map(s -> new SettlementResponse(s.getId(), s.getName(), s.getOwnerId()))
			.collect(Collectors.toList());
	}

	public List<SettlementResponse> getSettlementsByOwnerId(Long ownerId) {
		return settlementRepository.findByOwnerId(ownerId).stream()
			.map(s -> new SettlementResponse(s.getId(), s.getName(), s.getOwnerId()))
			.collect(Collectors.toList());
	}

	public SettlementResponse getSettlementById(Long id) {
		Settlement settlement = settlementRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("정산을 찾을 수 없습니다."));
		return new SettlementResponse(settlement.getId(), settlement.getName(), settlement.getOwnerId());
	}

	public void deleteSettlement(Long id) {
		settlementRepository.deleteById(id);
	}

	public void joinSettlement(Long userId, Long settlementId) {
		settlementRepository.addParticipant(userId, settlementId);
	}

}
