package com.euler.topguns.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.euler.topguns.entities.Account;
import com.euler.topguns.entities.Customer;
import com.euler.topguns.repositories.CustomerRepository;


@RestController
public class CustomerController {

	@Autowired
	CustomerRepository repository;
	

	@GetMapping("/api/topguns/customers")
	public List<Customer> getAllEmployees() {
		return repository.getAllCustomers();
	}
	
	@GetMapping("/api/topguns/customers/{id}")
	public ResponseEntity<Customer> details(@PathVariable int id) {
		Customer customer =  repository.getCustomerbyId(id);
		
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
        	return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
	}
	
	@GetMapping("/api/topguns/customers/{id}/accounts")
	public List<Account> getCustomerAccounts(@PathVariable Integer id) {
		List<Account> accounts = repository.getAccountsForCustomer(id);
		return accounts;
	}
	
	
	@PostMapping("/api/topguns/customers")
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		Customer newCust = repository.simpleInsertCustomer(customer);
		if (newCust != null)
			return new ResponseEntity<Customer>(newCust, HttpStatus.CREATED);
		else
			return new ResponseEntity<Customer>(newCust, HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/api/topguns/customers/{id}")
	public ResponseEntity<Void> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		int count = repository.updateCustomer(id, customer);
		if (count > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/api/topguns/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
		int count = repository.deleteCustomer(id);
		if (count > 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	

 }
