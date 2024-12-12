package com.example.tricount_clone.repository;

import com.example.tricount_clone.domain.Settlement;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

	public void deleteById(Long id) {
		String sql = "DELETE FROM settlements WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void addParticipant(Long userId, Long settlementId) {
		String sql = "INSERT INTO settlement_participants (user_id, settlement_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, userId, settlementId);
	}

	public Optional<Settlement> findById(Long id) {
		String sql = "SELECT * FROM settlements WHERE id = ?";
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, settlementRowMapper, id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Long saveAndReturnId(Settlement settlement) {
		String sql = "INSERT INTO settlements (name, owner_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, settlement.getName(), settlement.getOwnerId());

		// 마지막으로 생성된 ID 반환
		String getIdSql = "SELECT LAST_INSERT_ID()";
		return jdbcTemplate.queryForObject(getIdSql, Long.class);
	}

}
