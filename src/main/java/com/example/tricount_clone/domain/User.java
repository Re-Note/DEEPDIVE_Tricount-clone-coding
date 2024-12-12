package com.example.tricount_clone.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Long id; // Primary Key
	private String username; // Unique username
	private String password; // Encrypted password
	private String nickname; // Display name
}
