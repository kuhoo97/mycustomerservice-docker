package com.example.demo.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		log.info("within create customer service");
		return customerDao.save(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		log.info("within display customer service");
		List<Customer> c = customerDao.findAll();
		return c;
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.findById(id).get();
		log.info("within get by id in customer service");
		if(customer==null)
		{
			throw new CustomerNotFoundException("Not found");
		}
		return customer;
	}
	

	@Override
	public String deleteById(Integer id) {
		// TODO Auto-generated method stub
		Customer customer1 = customerDao.findById(id).get();
		log.info("within get by id in customer service");
		if(customer1==null)
		{
			throw new CustomerNotFoundException("Not found");
		}
		Customer tempCustomer = customer1;
		customerDao.delete(tempCustomer);
		log.info("deleted");
		return "Deleted";
		
	}

	@Override
	public Customer updateCustomer(Integer id, Customer customer) {
		Customer customer1 = customerDao.findById(id).get();
		log.info("within get by id in customer service");
		if(customer1==null)
		{
			throw new CustomerNotFoundException("Not found");
		}
		Customer tempCust = customer1 ;
		tempCust.setCustomerName(customer.getCustomerName());
		tempCust.setCustomerAddress(customer.getCustomerAddress());
		customerDao.save(tempCust);
		log.info("updated");
		return tempCust;
	}

}
