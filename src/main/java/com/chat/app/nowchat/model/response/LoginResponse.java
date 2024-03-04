package com.chat.app.nowchat.model.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String email;
    private String token;
}
