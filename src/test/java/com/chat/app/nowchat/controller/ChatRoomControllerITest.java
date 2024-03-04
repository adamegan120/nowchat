package com.chat.app.nowchat.controller;

import com.chat.app.nowchat.util.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatRoomControllerITest extends AbstractIntegrationTest {

    @BeforeEach
    void init() throws JsonProcessingException {
        super.headersSetUp();
        String token = getUserAccessToken("test@gmail.com","test_username","test_password");
        headers.setBearerAuth(token);
    }

    @Test
    void createChatRoom() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createdBy", "Testadam");
        jsonObject.put("name", "TestRoom");
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(),headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseString + port+ "/api/v1/chatrooms", request, String.class);

        assertEquals(response.getBody(),"Room Created :%s".formatted(jsonObject.get("name")));
    }
}
