package com.everisbootcamp.customers.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String idcustomer;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateafiliation = LocalDateTime.now(ZoneId.of("America/Lima"));

    private String namecustomer;
    private String lastnamecustomer;

    private String documentType;
    private String numberdocument;
    private String numberphone;
    private String emailaddress;

    private String typecustomer;

    public Customer(
        String namecustomer,
        String lastnamecustomer,
        String documentType,
        String numberdocument,
        String numberphone,
        String emailaddress,
        String typecustomer
    ) {
        this.namecustomer = namecustomer;
        this.lastnamecustomer = lastnamecustomer;
        this.documentType = documentType;
        this.numberdocument = numberdocument;
        this.numberphone = numberphone;
        this.emailaddress = emailaddress;
        this.typecustomer = typecustomer;
    }
}
