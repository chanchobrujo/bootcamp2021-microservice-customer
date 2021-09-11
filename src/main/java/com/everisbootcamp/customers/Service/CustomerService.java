package com.everisbootcamp.customers.Service;

import com.everisbootcamp.customers.Constant.Constants;
import com.everisbootcamp.customers.Data.Customer;
import com.everisbootcamp.customers.Interface.CustomerRepository;
import com.everisbootcamp.customers.Model.CustomerFrom;
import com.everisbootcamp.customers.Model.Response;
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
    CustomerRepository repository;

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(BindingResult bindinResult) {
        Response response = new Response(
            bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage().toString(),
            HttpStatus.NOT_ACCEPTABLE
        );

        return Mono.just(ResponseEntity.internalServerError().body(response.getResponse()));
    }

    public Mono<Response> save(CustomerFrom model) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        String message = Constants.Messages.REPET_DATA;

        Customer customer = new Customer(
            model.getNamecustomer(),
            model.getLastnamecustomer(),
            model.getDocumentType(),
            model.getNumberdocument(),
            model.getNumberphone(),
            model.getEmailaddress(),
            model.getTypecustomer()
        );

        if (
            repository
                .findAll()
                .toStream()
                .filter(c ->
                    c.getEmailaddress().equals(model.getEmailaddress()) ||
                    c.getNumberphone().equals(model.getNumberphone()) ||
                    c.getNumberdocument().equals(model.getNumberdocument())
                )
                .collect(Collectors.toList())
                .isEmpty()
        ) {
            if (
                Constants.TYPE_CUSTOMER
                    .stream()
                    .filter(c -> c.equals(customer.getTypecustomer()))
                    .collect(Collectors.toList())
                    .isEmpty() ||
                Constants.TYPE_DOCUMENT
                    .stream()
                    .filter(c -> c.equals(customer.getDocumentType()))
                    .collect(Collectors.toList())
                    .isEmpty()
            ) {
                message = Constants.Messages.INVALID_DATA;
            } else {
                status = HttpStatus.CREATED;
                message = Constants.Messages.CORRECT_DATA;
                repository.save(customer).subscribe();
            }
        }

        return Mono.just(new Response(message, status));
    }

    public Mono<Customer> getByIdcustomer(String id) {
        return repository.findByIdcustomer(id);
    }

    public Flux<Customer> findAll() {
        return repository.findAll();
    }
}
/*
 * application.yml server: port: 9590
 *
 * spring: application: name: microservices-customer data: mongodb: database:
 * microservicescustomer uri: mongodb://localhost:27017/microservicescustomer
 *
 * application.properties -- spring.application.name=microservices-customer
 * server.port=9590 spring.data.mongodb.database=microservicescustomer
 *
 * #spring.data.mongodb.uri=mongodb+srv://palmachris7:bank2208@cluster0.a1f2l.
 * mongodb.net/test
 *
 */
