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

	// 정산 생성
	@PostMapping
	public ResponseEntity<String> createSettlement(@RequestBody SettlementRequest request) {
		settlementService.createSettlement(request);
		return ResponseEntity.ok("정산이 성공적으로 생성되었습니다.");
	}

	// 정산 생성 및 참여
	@PostMapping("/join")
	public ResponseEntity<String> createAndJoinSettlement(@RequestBody SettlementRequest request) {
		Long settlementId = settlementService.createSettlementAndReturnId(request); // ID 반환
		settlementService.joinSettlement(request.getOwnerId(), settlementId); // 참여
		return ResponseEntity.ok("정산이 성공적으로 생성되고 참여되었습니다.");
	}

	// 모든 정산 조회
	@GetMapping
	public ResponseEntity<List<SettlementResponse>> getAllSettlements() {
		List<SettlementResponse> settlements = settlementService.getAllSettlements();
		return ResponseEntity.ok(settlements);
	}

	// 특정 소유자의 정산 조회
	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<List<SettlementResponse>> getSettlementsByOwnerId(@PathVariable Long ownerId) {
		List<SettlementResponse> settlements = settlementService.getSettlementsByOwnerId(ownerId);
		return ResponseEntity.ok(settlements);
	}

	// 특정 정산 조회
	@GetMapping("/{id}")
	public ResponseEntity<SettlementResponse> getSettlement(@PathVariable Long id) {
		SettlementResponse settlement = settlementService.getSettlementById(id);
		return ResponseEntity.ok(settlement);
	}

	// 정산 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSettlement(@PathVariable Long id) {
		settlementService.deleteSettlement(id);
		return ResponseEntity.ok("정산이 삭제되었습니다.");
	}

	// 정산 참여
	@PostMapping("/{id}/join")
	public ResponseEntity<String> joinSettlement(@PathVariable Long id, @RequestParam Long userId) {
		settlementService.joinSettlement(userId, id);
		return ResponseEntity.ok("정산에 성공적으로 참여하였습니다.");
	}
}
