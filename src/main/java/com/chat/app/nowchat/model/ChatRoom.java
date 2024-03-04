package com.chat.app.nowchat.model;

import lombok.Data;

import java.util.Set;

@Data
public class ChatRoom {
    private String id;
    private String name;
    private String createdBy;
    private Set<User> members;
}
