package com.example.tricount_clone.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Settlement {
	private Long id; // Primary Key
	private String name; // 정산 이름
	private Long ownerId; // 정산을 만든 사용자 ID
}
