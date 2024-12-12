package com.example.tricount_clone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExpenseResponse {
	private Long id; // 지출 ID
	private String name; // 지출 이름
	private BigDecimal amount; // 지출 금액
	private LocalDate date; // 지출 날짜
	private Long settlementId; // 정산 ID
	private Long userId; // 지출한 사용자 ID
}
