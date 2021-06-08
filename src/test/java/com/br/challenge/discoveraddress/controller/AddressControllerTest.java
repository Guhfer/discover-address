package com.br.challenge.discoveraddress.controller;

import com.br.challenge.discoveraddress.BaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class AddressControllerTest extends BaseIntegrationTest {

    @BeforeEach
    public void setup() {
        wireMockServer.start();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }


    @Test
    public void shouldReturnAddressCorrect() throws IOException {

        super.mockAddressIntegrationGet("01001000", HttpStatus.OK,
                "/return-address-ok.json");

        given().log().everything()
                .headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .when()
                .get(getUrl("/addresses/{cep}").replace("{cep}", "01001000"))
        .then().log().everything()
                .assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnAddressNotFound() throws IOException {

        super.mockAddressIntegrationGet("12345678", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12345670", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12345600", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12345000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12340000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12300000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("12000000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("10000000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("00000000", HttpStatus.OK,
                "/return-address-not-found.json");

        given().log().everything()
                .headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(getUrl("/addresses/{cep}").replace("{cep}", "12345678"))
                .then().log().everything()
                .assertThat().statusCode(HttpStatus.NOT_FOUND.value());
    }


    @Test
    public void shouldReturnAddressCorrectAfterManyRetries() throws IOException {

        super.mockAddressIntegrationGet("87654321", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("87654320", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("87654300", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("87654000", HttpStatus.OK,
                "/return-address-not-found.json");

        super.mockAddressIntegrationGet("87650000", HttpStatus.OK,
                "/return-address-ok.json");

        given().log().everything()
                .headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(getUrl("/addresses/{cep}").replace("{cep}", "87654321"))
                .then().log().everything()
                .assertThat().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnServerErrorWhenDontExpectedException() throws IOException {

        super.mockAddressIntegrationGet("12332112", HttpStatus.INTERNAL_SERVER_ERROR,
                "/return-address-not-found.json");


        given().log().everything()
                .headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(getUrl("/addresses/{cep}").replace("{cep}", "12332112"))
                .then().log().everything()
                .assertThat().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }



}
