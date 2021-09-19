package com.everisbootcamp.customers.Controller;

import com.everisbootcamp.customers.CustomersApplication;
import com.everisbootcamp.customers.Service.CustomerService; 
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CustomersApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerCustomer {

    @Mock
    private CustomerService service;

    @InjectMocks
    CustomerController controller;

    @Before
    public void init() {}
}
