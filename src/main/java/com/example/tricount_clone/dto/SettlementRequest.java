package com.example.tricount_clone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettlementRequest {
	private String name; // 정산 이름
	private Long ownerId; // 정산 생성자 ID
}
