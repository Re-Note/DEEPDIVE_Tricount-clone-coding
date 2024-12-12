package com.example.tricount_clone.controller;

import com.example.tricount_clone.dto.BalanceResponse;
import com.example.tricount_clone.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balances")
public class BalanceController {
	private final BalanceService balanceService;

	public BalanceController(BalanceService balanceService) {
		this.balanceService = balanceService;
	}

	@GetMapping("/settlement/{settlementId}")
	public ResponseEntity<List<BalanceResponse>> getBalances(@PathVariable Long settlementId) {
		List<BalanceResponse> balances = balanceService.calculateBalances(settlementId);
		return ResponseEntity.ok(balances);
	}

	@GetMapping
	public ResponseEntity<List<BalanceResponse>> getSettleBalance(@RequestParam Long settlementId) {
		List<BalanceResponse> balances = balanceService.calculateBalances(settlementId);
		return ResponseEntity.ok(balances);
	}

}
