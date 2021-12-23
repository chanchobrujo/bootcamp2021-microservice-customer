package com.everisbootcamp.customers.Service;

import com.everisbootcamp.customers.Constant.Constants;
import com.everisbootcamp.customers.Constant.enums.MessagesError;
import com.everisbootcamp.customers.Constant.enums.MessagesSuccess;
import com.everisbootcamp.customers.Data.Customer;
import com.everisbootcamp.customers.Interface.CustomerRepository;
import com.everisbootcamp.customers.Model.CustomerFrom;
import com.everisbootcamp.customers.Model.Response; 

import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private VerifyService verifyService;
    
    private Optional<Customer> findRepetedData(String email, String phone, String number){
    	return this.repository.findAll().toStream()
    			.filter(customer -> 
    				customer.getEmailaddress().toUpperCase().equals(email.toUpperCase()) ||
    				customer.getNumberphone().toUpperCase().equals(phone.toUpperCase()) ||
    				customer.getNumberdocument().toUpperCase().equals(number.toUpperCase()))
    			.findFirst();
    }

    public Mono<Response> save(CustomerFrom model) { 
    	Response response = new Response();
        
        Boolean verifyRepetData = this.findRepetedData(
        		model.getEmailaddress(), model.getNumberphone(), 
        		model.getNumberdocument()).isEmpty();

        if ( verifyRepetData ) {
        	Boolean verifyCustomerType = this.verifyService
        			.verifyTypeCustomer(model.getTypecustomer())
        			.isEmpty();
        	Boolean verifyDocumentType = this.verifyService.
        			verifyTypeDocument(model.getDocumentType())
        			.isEmpty();
        	
            if (verifyCustomerType || verifyDocumentType) {
            	response = new Response(MessagesError.NOTFOUND_DATA);
            } else { 
                Customer customer = new Customer();  
                
                Customer.builder()
                	.namecustomer(model.getNamecustomer())
                	.lastnamecustomer(model.getLastnamecustomer())
                	.documentType(model.getDocumentType())
                	.numberdocument(model.getNumberdocument())
                	.numberphone(model.getNumberphone())
                	.emailaddress(model.getEmailaddress())
                	.typecustomer(model.getTypecustomer()); 
                
                repository.save(customer).subscribe();

            	response = new Response(MessagesSuccess.SUCCESS_REGISTER); 
            }
        } 

        return Mono.just(new Response(MessagesSuccess.SUCCESS_REGISTER));
    }

    public Mono<Customer> getByIdcustomer(String id) {
        return repository.findByIdcustomer(id);
    }

    public Flux<Customer> findAll() {
        return repository.findAll();
    }
}
