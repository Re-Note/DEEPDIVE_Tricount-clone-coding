package com.example.tricount_clone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BalanceResponse {
	private Long senderUserId; // 송금하는 사용자 ID
	private String senderUserName; // 송금하는 사용자 이름
	private BigDecimal sendAmount; // 송금 금액
	private Long receiverUserId; // 송금을 받는 사용자 ID
	private String receiverUserName; // 송금을 받는 사용자 이름
}
