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
}
