package com.everisbootcamp.customers.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.everisbootcamp.customers.Constant.Constants;

@Service
public class VerifyService {
	public Optional<String> verifyTypeCustomer(String type) {
    	return Constants.TYPE_CUSTOMER.stream() 
    			.filter(c -> c.toUpperCase().equals(type.toUpperCase()))
    			.findFirst();
    } 

	public Optional<String> verifyTypeDocument(String type) {
    	return Constants.TYPE_DOCUMENT.stream() 
    			.filter(c -> c.toUpperCase().equals(type.toUpperCase()))
    			.findFirst();
    } 
}
