package com.br.challenge.discoveraddress.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FindAddressIntegration {

    @Value("${services.viaCep.url}")
    String baseUrl;
}
