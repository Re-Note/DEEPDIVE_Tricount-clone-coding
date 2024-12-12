package com.example.tricount_clone.repository;

import com.example.tricount_clone.domain.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;

	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 회원 저장
	public void save(User user) {
		String sql = "INSERT INTO users (username, password, nickname) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getNickname());
	}

	// 유저 이름으로 조회 (중복 확인)
	public boolean existsByUsername(String username) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
		return count != null && count > 0;
	}

	// 유저 ID로 이름 조회
	public String getUserNameById(Long userId) {
		String sql = "SELECT nickname FROM users WHERE id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, String.class, userId);
		} catch (EmptyResultDataAccessException e) {
			// 결과가 없는 경우 null 반환
			return null;
		}
	}

}
