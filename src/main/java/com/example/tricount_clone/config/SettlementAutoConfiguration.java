package com.example.tricount_clone.config;

import com.example.tricount_clone.repository.SettlementRepository;
import com.example.tricount_clone.service.SettlementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SettlementAutoConfiguration {

	@Bean
	public SettlementRepository settlementRepository(JdbcTemplate jdbcTemplate) {
		return new SettlementRepository(jdbcTemplate);
	}

	@Bean
	public SettlementService settlementService(SettlementRepository settlementRepository) {
		return new SettlementService(settlementRepository);
	}
}
