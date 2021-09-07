package com.everisbootcamp.customers.Interface;

import com.everisbootcamp.customers.Data.Customer; 
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    public Mono<Customer> findByIdcustomer(String id);

    public Mono<Boolean> existsByNumberdocument(String numberdocument);

    public Mono<Boolean> existsByEmailaddress(String emailaddress);

    public Mono<Boolean> existsByNumberphone(String numberphone);
}
