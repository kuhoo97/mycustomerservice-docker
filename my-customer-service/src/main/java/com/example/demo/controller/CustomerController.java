package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomerErrorResponse;
import com.example.demo.exception.CustomerNotFoundException;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {
	
	private CustomerService customerService;
	private Environment environment;
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e){
		CustomerErrorResponse response = new CustomerErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setErrorReportingTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleTypeMisMatchedException(Exception e) {
		CustomerErrorResponse response = new CustomerErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setErrorReportingTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	@Autowired
	public CustomerController(CustomerService customerService ,  Environment environment) {
		
		this.customerService = customerService;
		this.environment = environment;
	}

	@GetMapping
	public ResponseEntity<String> getStatus()
	{
		return new ResponseEntity<String>("customer service is up and running on port: "+environment.getProperty("local.server.port"),HttpStatus.OK);
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
	{
		log.info("within post mapping");
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		log.info("within get all method");
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
	}
	
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getById(@PathVariable("id") Integer id)
	{
		log.info("within get by id");
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getById(id));
		
	}
	
	
	@PutMapping("/customers/update/{id}")
	public ResponseEntity<Customer>updateBookByBookId(@PathVariable("id") Integer id,@RequestBody Customer customer)
	{
		log.info("within put mapping");
		Customer c2=customerService.updateCustomer(id, customer);
		return ResponseEntity.status(HttpStatus.OK).body(c2);
	}
	
	@DeleteMapping("/customers/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id)
	{
		log.info("within delete by book_id");
		return ResponseEntity.ok(customerService.deleteById(id));
	}
	

	
	
	
	
}
