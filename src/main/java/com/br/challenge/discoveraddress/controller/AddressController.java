package com.br.challenge.discoveraddress.controller;

import com.br.challenge.discoveraddress.controller.resource.AddressResponse;
import com.br.challenge.discoveraddress.service.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/{cep}")
    @ApiOperation(value = "Get Address by CEP")
    public ResponseEntity<AddressResponse> getAddressByCep(@PathVariable String cep) {

        log.info(this.getClass() + " Get address by CEP: " + cep);

        return ResponseEntity.status(HttpStatus.OK)
                .body(addressService.findAddressByCep(cep));
    }
}
