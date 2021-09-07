package com.everisbootcamp.customers.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFrom {

    @NotBlank(message = "El campo nombre no debe estar vacio.")
    private String namecustomer;

    @NotBlank(message = "El campo apellido no debe estar vacio.")
    private String lastnamecustomer;

    @NotBlank(message = "El campo tipo de documento no debe estar vacio.")
    private String documentType;

    @Size(min = 7, message = "Como mínimo el número de documento debe tener 7 cifras.")
    @NotBlank(message = "El campo número de documento no debe estar vacio.")
    private String numberdocument;

    @Size(min = 9, message = "Como mínimo el número telefónico debe tener 9 cifras.")
    @NotBlank(message = "El campo número telefónico no debe estar vacio.")
    private String numberphone;

    @Email
    @NotBlank(message = "El campo dirección de correo electrónico no debe estar vacio.")
    private String emailaddress;

    @NotBlank(message = "El campo tipo de cliente no debe estar vacio.")
    private String typecustomer;
}
