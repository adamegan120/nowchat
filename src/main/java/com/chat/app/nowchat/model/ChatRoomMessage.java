package com.chat.app.nowchat.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatRoomMessage {
    private String id;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
}
