package com.euler.topguns.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet row, int rowNum) throws SQLException {
		
		Customer customer = new Customer();
		customer.setId(row.getInt("id"));
		customer.setFirstName(row.getString("first_name"));
		customer.setLastName(row.getString("last_name"));
		customer.setEmail(row.getString("email"));
		return customer;

	}
	
}
