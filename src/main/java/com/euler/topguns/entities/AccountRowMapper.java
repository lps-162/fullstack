package com.euler.topguns.entities;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet row, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(row.getInt("id"));
		account.setAccountNumber(row.getString("acc_no"));
		account.setAccountBranch(row.getString("branch"));
		account.setAccountBalance(row.getDouble("balance"));
		return account;
	}
	
}
