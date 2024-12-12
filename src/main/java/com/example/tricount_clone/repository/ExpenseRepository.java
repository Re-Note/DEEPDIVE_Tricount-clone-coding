package com.example.tricount_clone.repository;

import com.example.tricount_clone.domain.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepository {
	private final JdbcTemplate jdbcTemplate;

	public ExpenseRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<Expense> expenseRowMapper = (rs, rowNum) -> Expense.builder()
		.id(rs.getLong("id"))
		.name(rs.getString("name"))
		.amount(rs.getBigDecimal("amount"))
		.date(rs.getDate("date").toLocalDate())
		.settlementId(rs.getLong("settlement_id"))
		.userId(rs.getLong("user_id"))
		.build();

	// 지출 저장
	public void save(Expense expense) {
		String sql = "INSERT INTO expenses (name, amount, date, settlement_id, user_id) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, expense.getName(), expense.getAmount(), expense.getDate(), expense.getSettlementId(), expense.getUserId());
	}

	// 특정 정산의 지출 목록 조회
	public List<Expense> findBySettlementId(Long settlementId) {
		String sql = "SELECT * FROM expenses WHERE settlement_id = ?";
		return jdbcTemplate.query(sql, expenseRowMapper, settlementId);
	}
}
