package com.everisbootcamp.customers.Interface;

import com.everisbootcamp.customers.Data.Customer;
import java.util.Map;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    public Mono<Map<String, Object>> save(Customer model);

    public Mono<Customer> findByIdcustomer(String id);
}
