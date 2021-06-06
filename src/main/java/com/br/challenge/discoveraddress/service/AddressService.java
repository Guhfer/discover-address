package com.br.challenge.discoveraddress.service;

import com.br.challenge.discoveraddress.integration.FindAddressIntegration;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final FindAddressIntegration findAddressIntegration;

    public AddressService(FindAddressIntegration findAddressIntegration) {
        this.findAddressIntegration = findAddressIntegration;
    }


}
