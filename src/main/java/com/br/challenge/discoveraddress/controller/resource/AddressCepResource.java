package com.br.challenge.discoveraddress.controller.resource;

import lombok.Data;

@Data
public class AddressCepResource {

    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private Integer ibge;

    private Integer gia;

    private Integer ddd;

    private Integer siafi;
}
