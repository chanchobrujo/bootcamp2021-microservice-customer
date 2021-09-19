package com.everisbootcamp.customers.Service;

import com.everisbootcamp.customers.Data.Customer;
import com.everisbootcamp.customers.Data.DataProvider;
import com.everisbootcamp.customers.Interface.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    CustomerService serviceMock;
    CustomerRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CustomerRepository.class);
        serviceMock = new CustomerService();
    }

    @Test
    void getByIdcustomer() {
        Customer customerRequest = DataProvider.CustomerRequestA();
        customerRequest.setIdcustomer("123");
        Mockito
            .when(repository.findByIdcustomer(customerRequest.getIdcustomer()))
            .thenReturn(Mono.error(() -> null));

        Mono<Customer> customerId = serviceMock.getByIdcustomer(customerRequest.getIdcustomer());

        StepVerifier.create(customerId).expectNext(customerRequest).verifyComplete();
    }
}
