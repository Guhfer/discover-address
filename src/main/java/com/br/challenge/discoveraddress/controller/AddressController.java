package com.br.challenge.discoveraddress.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {


    @GetMapping("/{cep}")
    public ResponseEntity getAddressByCep(@PathVariable String cep) {

    }
}
