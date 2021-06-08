package com.br.challenge.discoveraddress.service;

import com.br.challenge.discoveraddress.controller.resource.AddressResponse;
import com.br.challenge.discoveraddress.exception.InvalidCepException;
import com.br.challenge.discoveraddress.integration.FindAddressIntegration;
import com.br.challenge.discoveraddress.mapper.AddressMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class AddressService {

    private final FindAddressIntegration findAddressIntegration;
    private final AddressMapper addressMapper;

    public AddressService(FindAddressIntegration findAddressIntegration, AddressMapper addressMapper) {
        this.findAddressIntegration = findAddressIntegration;
        this.addressMapper = addressMapper;
    }

    public AddressResponse findAddressByCep(String cep) {
        validateCep(cep);

        AddressResponse addressResponse = addressMapper
                .addressCepResourceToAddressResponse(findAddressIntegration.getAddressByCep(cep));

        if(addressResponse.checkNullAndIsEmpty()) {
            addressResponse = verifyIfAnyAddressExists(cep);
        }

        return addressResponse;
    }

    @SneakyThrows
    private AddressResponse verifyIfAnyAddressExists(String cep) {
        AddressResponse addressResponse = null;
        char[] charCep = cep.toCharArray();
        log.info("Try find address by CEP: " + cep);
        for (int i = (cep.length()-1); i >= 0; i--) {
            charCep[i] = '0';
            addressResponse = addressMapper.addressCepResourceToAddressResponse(
                    findAddressIntegration.getAddressByCep(String.valueOf(charCep)));
            if (!addressResponse.checkNullAndIsEmpty()) {
                log.info("Address found CEP: " + String.valueOf(charCep));
                break;
            }
        }

        if(addressResponse.checkNullAndIsEmpty()) {
            log.error("Invalid CEP: " + cep);
            throw new InvalidCepException();
        }

        return addressResponse;
    }

    @SneakyThrows
    private void validateCep(String cep) {
        final Pattern pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");
        if (!pattern.matcher(cep).matches()) {
            log.error(" Invalid CEP: " + cep);
            throw new InvalidCepException();
        }
    }
}
