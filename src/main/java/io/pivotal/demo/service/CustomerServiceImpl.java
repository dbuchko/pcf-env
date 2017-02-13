package io.pivotal.demo.service;

import io.pivotal.demo.domain.Customer;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("customerService")
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
}
