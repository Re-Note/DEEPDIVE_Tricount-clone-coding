package com.example.tricount_clone.controller;

import com.example.tricount_clone.dto.SettlementRequest;
import com.example.tricount_clone.dto.SettlementResponse;
import com.example.tricount_clone.service.SettlementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
	private final SettlementService settlementService;

	public SettlementController(SettlementService settlementService) {
		this.settlementService = settlementService;
	}

	@PostMapping
	public ResponseEntity<String> createSettlement(@RequestBody SettlementRequest request) {
		settlementService.createSettlement(request);
		return ResponseEntity.ok("정산이 성공적으로 생성되었습니다.");
	}

	@GetMapping
	public ResponseEntity<List<SettlementResponse>> getAllSettlements() {
		List<SettlementResponse> settlements = settlementService.getAllSettlements();
		return ResponseEntity.ok(settlements);
	}

	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<SettlementResponse>> getSettlementsByOwnerId(@PathVariable Long ownerId) {
		List<SettlementResponse> settlements = settlementService.getSettlementsByOwnerId(ownerId);
		return ResponseEntity.ok(settlements);
	}
}
