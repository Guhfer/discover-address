package com.br.challenge.discoveraddress.controller.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.stream.Stream;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class AddressResponse {

    private String rua;

    private String bairro;

    private String cidade;

    private String estado;

    public boolean checkNullAndIsEmpty() {
        return Stream.of(rua, bairro, cidade, estado)
                .anyMatch(s -> (s == null) || (s.isEmpty()));
    }

}
