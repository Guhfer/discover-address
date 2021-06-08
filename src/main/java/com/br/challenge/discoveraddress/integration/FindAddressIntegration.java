package com.br.challenge.discoveraddress.integration;

import com.br.challenge.discoveraddress.controller.resource.AddressCepResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@CacheConfig(cacheNames={"response"})
public class FindAddressIntegration {

    private final String baseUrl;
    private final String pathGet;

    public FindAddressIntegration(@Value("${services.viaCep.url}") String baseUrl,
                                  @Value("${services.viaCep.path.get}") String pathGet) {
        this.baseUrl = baseUrl;
        this.pathGet = pathGet;
    }

    @Cacheable
    public AddressCepResource getAddressByCep (String cep) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AddressCepResource> response = restTemplate.getForEntity(buildUrl(cep), AddressCepResource.class);

        log.info(this.getClass().getName() + " Get address by ViaCep API response: " + response.toString());

        return response.getBody();
    }

    private String buildUrl (String cep) {
        return (baseUrl.concat(pathGet)).replace("{cep}", cep);
    }

}
