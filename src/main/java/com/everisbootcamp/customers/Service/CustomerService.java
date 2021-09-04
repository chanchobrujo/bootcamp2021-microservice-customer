package com.everisbootcamp.customers.Service;

import com.everisbootcamp.customers.Data.Customer;
import com.everisbootcamp.customers.Interface.CustomerRepository;
import com.everisbootcamp.customers.Model.CustomerFrom;
import com.everisbootcamp.customers.Model.MessageFrom;
import com.everisbootcamp.customers.Model.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(BindingResult bindinResult) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_ACCEPTABLE);

        for (int i = 0; i < bindinResult.getAllErrors().size(); i++) response.put(
            "message",
            bindinResult.getAllErrors().get(0).getDefaultMessage()
        );

        return Mono.just(ResponseEntity.internalServerError().body(response));
    }

    public Mono<Map<String, Object>> save(String idcustomer, CustomerFrom model) {
        String status = HttpStatus.CREATED.toString(), message = "Cliente registrado.";

        Customer customer = new Customer(
            model.getNamecustomer(),
            model.getLastnamecustomer(),
            model.getDocumentType(),
            model.getNumberdocument(),
            model.getNumberphone(),
            model.getEmailaddress()
        );

        repository.save(customer);

        return Mono.just(new Response(status, message).getResponse());
    }

    public Mono<Customer> getByIdcustomer(String id) {
        return repository.findByIdcustomer(id);
    }

    public Flux<Customer> findAll() {
        return repository.findAll();
    }
}
