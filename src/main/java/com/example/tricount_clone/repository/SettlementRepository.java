package com.example.tricount_clone.repository;

import com.example.tricount_clone.domain.Settlement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SettlementRepository {
	private final JdbcTemplate jdbcTemplate;

	public SettlementRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<Settlement> settlementRowMapper = (rs, rowNum) -> Settlement.builder()
		.id(rs.getLong("id"))
		.name(rs.getString("name"))
		.ownerId(rs.getLong("owner_id"))
		.build();

	// 정산 저장
	public void save(Settlement settlement) {
		String sql = "INSERT INTO settlements (name, owner_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, settlement.getName(), settlement.getOwnerId());
	}

	// 모든 정산 조회
	public List<Settlement> findAll() {
		String sql = "SELECT * FROM settlements";
		return jdbcTemplate.query(sql, settlementRowMapper);
	}

	// 특정 사용자 소유 정산 조회
	public List<Settlement> findByOwnerId(Long ownerId) {
		String sql = "SELECT * FROM settlements WHERE owner_id = ?";
		return jdbcTemplate.query(sql, settlementRowMapper, ownerId);
	}
}
