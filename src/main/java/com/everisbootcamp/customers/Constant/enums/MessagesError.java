package com.everisbootcamp.customers.Constant.enums;

import lombok.Getter;

@Getter
public enum MessagesError {
    INCORRECT_DATA(400, "Datos incorrectos."),
    REPETED_DATA(400, "Datos repetidos."),
    NOTFOUND_DATA(404, "Datos no encontrados.");

    private Integer cod;
    private String messages;

    private MessagesError(Integer cod, String messages) {
        this.cod = cod;
        this.messages = messages;
    }
}
