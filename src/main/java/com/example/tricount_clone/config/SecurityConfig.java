package com.example.tricount_clone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/users/signup").permitAll() // 회원가입 API 인증 제외
				.anyRequest().authenticated() // 나머지 요청은 인증 필요
			);

		return http.build();
	}
}
