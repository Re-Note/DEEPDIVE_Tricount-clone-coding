package com.example.tricount_clone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ExpenseRequest {
	private String name; // 지출 이름
	private BigDecimal amount; // 지출 금액
	private LocalDate date; // 지출 날짜
	private Long settlementId; // 정산 ID
	private Long userId; // 지출한 사용자 ID
}
