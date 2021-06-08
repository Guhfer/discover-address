package com.br.challenge.discoveraddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DiscoverAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoverAddressApplication.class, args);
    }

}
