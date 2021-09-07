package com.everisbootcamp.customers.Service;

import com.everisbootcamp.customers.Constant.Constants;
import com.everisbootcamp.customers.Data.Customer;
import com.everisbootcamp.customers.Interface.CustomerRepository;
import com.everisbootcamp.customers.Model.CustomerFrom;
import com.everisbootcamp.customers.Model.Response;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(BindingResult bindinResult) {
        Response response = new Response(HttpStatus.NOT_ACCEPTABLE.toString(),
                bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage().toString());

        return Mono.just(ResponseEntity.internalServerError().body(response.getResponse()));
    }

    public Mono<Map<String, Object>> save(String idcustomer, Customer customer) {
        String status = HttpStatus.NOT_ACCEPTABLE.toString(), message = Constants.Messages.REPET_DATA;

        if (Constants.TYPE_CUSTOMER.stream().filter(c -> c.equals(customer.getTypecustomer()))
                .collect(Collectors.toList()).isEmpty()
                || Constants.TYPE_DOCUMENT.stream().filter(c -> c.equals(customer.getDocumentType()))
                        .collect(Collectors.toList()).isEmpty()) {

            message = Constants.Messages.INVALID_DATA;

        } else {

            if (idcustomer != null)
                customer.setIdcustomer(idcustomer);

            status = HttpStatus.CREATED.toString();
            message = Constants.Messages.CORRECT_DATA;
            repository.save(customer).subscribe();
        }

        return Mono.just(new Response(status, message).getResponse());
    }

    public Mono<Map<String, Object>> register(CustomerFrom model) {
        Customer customer = new Customer(model.getNamecustomer(), model.getLastnamecustomer(), model.getDocumentType(),
                model.getNumberdocument(), model.getNumberphone(), model.getEmailaddress(), model.getTypecustomer());

        return save(null, customer);
    }

    public Mono<Map<String, Object>> update(String idcustomer, CustomerFrom model) {
        Customer customer = new Customer(model.getNamecustomer(), model.getLastnamecustomer(), model.getDocumentType(),
                model.getNumberdocument(), model.getNumberphone(), model.getEmailaddress(), model.getTypecustomer());

        if (!repository.existsByIdcustomer(idcustomer).block())
            return Mono.just(
                    new Response(HttpStatus.NOT_FOUND.toString(), Constants.Messages.CLIENT_NOT_FOUND).getResponse());

        return save(idcustomer, customer);
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
