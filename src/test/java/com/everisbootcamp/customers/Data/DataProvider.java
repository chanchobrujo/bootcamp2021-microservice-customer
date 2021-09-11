package com.everisbootcamp.customers.Data;

public class DataProvider {

    public static Customer CustomerRequestA() {
        return Customer
            .builder()
            .idcustomer("ABC")
            .namecustomer("Kevin")
            .lastnamecustomer("Palma")
            .documentType("DNI")
            .numberdocument("75395109")
            .numberphone("951753094")
            .emailaddress("GAA@gmail.com")
            .typecustomer("Personal")
            .build();
    }

    public static Customer CustomerRequestB() {
        return Customer
            .builder()
            .idcustomer("DEF")
            .namecustomer("Inkafarma")
            .lastnamecustomer(null)
            .documentType("RUC")
            .numberdocument("2011111111")
            .numberphone("951963094")
            .emailaddress("Inkafarma@gmail.com")
            .typecustomer("Empresarial")
            .build();
    }
}
