package com.example.tricount_clone.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
	private Long id; // Primary Key
	private String name; // 지출 이름
	private BigDecimal amount; // 지출 금액
	private LocalDate date; // 지출 날짜
	private Long settlementId; // 정산 ID (Foreign Key)
	private Long userId; // 지출한 사용자 ID (Foreign Key)
}
