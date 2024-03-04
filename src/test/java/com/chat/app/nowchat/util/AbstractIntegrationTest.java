package com.chat.app.nowchat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String baseString = "http://localhost:";

    protected HttpHeaders headers;

    @BeforeEach
    protected void headersSetUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }


    protected String getUserAccessToken(String email, String username, String password) throws JsonProcessingException {
        String registerUrl = baseString + port + "/api/users";
        HttpEntity<String> registerRequest = new HttpEntity<>("{\"username\":\"%s\",\"password\":\"%s\",\"email\":\"%s\"}".formatted(username,password,email),headers);
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(registerUrl, registerRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(tokenResponse.getBody());
        return jsonNode.get("access_token").asText();
    }
}
