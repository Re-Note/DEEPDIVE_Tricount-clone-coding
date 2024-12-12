package com.example.tricount_clone.controller;

import com.example.tricount_clone.dto.ExpenseRequest;
import com.example.tricount_clone.dto.ExpenseResponse;
import com.example.tricount_clone.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping
	public ResponseEntity<String> createExpense(@RequestBody ExpenseRequest request) {
		expenseService.createExpense(request);
		return ResponseEntity.ok("지출이 성공적으로 추가되었습니다.");
	}

	@GetMapping("/settlement/{settlementId}")
	public ResponseEntity<List<ExpenseResponse>> getExpensesBySettlementId(@PathVariable Long settlementId) {
		List<ExpenseResponse> expenses = expenseService.getExpensesBySettlementId(settlementId);
		return ResponseEntity.ok(expenses);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
		expenseService.deleteExpense(id);
		return ResponseEntity.ok("지출이 삭제되었습니다.");
	}

}
