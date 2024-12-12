package com.example.tricount_clone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettlementResponse {
	private Long id; // 정산 ID
	private String name; // 정산 이름
	private Long ownerId; // 정산 생성자 ID
}
