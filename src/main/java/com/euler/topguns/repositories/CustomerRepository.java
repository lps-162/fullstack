package com.euler.topguns.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.euler.topguns.entities.Account;
import com.euler.topguns.entities.AccountRowMapper;
import com.euler.topguns.entities.Customer;
import com.euler.topguns.entities.CustomerRowMapper;

@Repository
@Transactional
public class CustomerRepository {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	
	public List<Customer> getAllCustomers() {
		String sql = "SELECT id, first_name, last_name, email FROM customer order by id";     
		RowMapper<Customer> rowMapper = new CustomerRowMapper();
		List<Customer> customers = this.jdbcTemplate.query(sql, rowMapper);
		return customers;
	}
	
	public Customer getCustomerbyId(int id) {
		String sql = "SELECT id, first_name, last_name, email FROM customer where id = ?";
		RowMapper<Customer> rowMapper = new CustomerRowMapper();
		Customer customer;
		try {
			customer = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
		} catch(EmptyResultDataAccessException erd) {
			return null;
		}
		return customer;
	}
	
	public int addCustomer(Customer customer) {
		String sql = "INSERT INTO customer (first_name, last_name, email) values (?, ?, ?)";
		int rowsAffected = jdbcTemplate.update(sql, 
							customer.getFirstName(), 
							customer.getLastName(),
							customer.getEmail()
						);
		return rowsAffected;
	}
	
	public Customer simpleInsertCustomer(Customer customer) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
		          .withTableName("customer")
		          .usingGeneratedKeyColumns("id");
		int custId = simpleJdbcInsert.executeAndReturnKey(customer.toMap()).intValue();
		customer.setId(custId);
		return customer;
	}
	
	
	public int updateCustomer(int id, Customer customer) {
		String sql = "UPDATE customer SET first_name=?, last_name=?, email=? WHERE id=?";
		int noOfRows = jdbcTemplate.update(sql, 
							customer.getFirstName(), 
							customer.getLastName(),
							customer.getEmail(),
							id
							);
		return noOfRows;
	}
	
	public int deleteCustomer(int id) {
		String sql = "DELETE FROM customer WHERE id=?";
		int rowsAffected = jdbcTemplate.update(sql, id);
		return rowsAffected;
	}
	
	
	public List<Account> getAccountsForCustomer(int customerId) {
		String sql = "SELECT id, acc_no, branch, "
				+ "balance FROM account where cust_id=?";
		RowMapper<Account> rowMapper = new AccountRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, customerId);
	}
	
}

