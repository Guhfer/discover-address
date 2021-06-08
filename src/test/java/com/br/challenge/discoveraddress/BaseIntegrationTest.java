package com.br.challenge.discoveraddress;

import com.br.challenge.discoveraddress.util.FileUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    private static final Integer PORT = 8082;

    public WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().port(8082));

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @Value("${localhost.url}")
    private String localhost;

    @Value("${services.viaCep.path.get}")
    private String pathUrl;

    @LocalServerPort
    private int randomPort;

    protected String getUrl(String path) {
        if(path == null) {
            throw new RuntimeException("Parameter pathUrl cant be null");
        }
        return this.localhost.replace("{port}", String.valueOf(this.randomPort)).concat(path);
    }

    protected void mockAddressIntegrationGet(String cep, HttpStatus status, String jsonFile) throws IOException {

        String url = pathUrl.replace("{cep}", cep);

        wireMockServer.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(status.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(FileUtils.getFile(jsonFile))));

    }
}
