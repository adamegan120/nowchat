package com.chat.app.nowchat.controller;

import com.chat.app.nowchat.model.request.ChatRoomRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("api/v1/chatrooms")
@RestController
@Slf4j
public class ChatRoomController {

    @PostMapping()
    //Room name
    //Created By
    public ResponseEntity<String> createRoom(@RequestBody ChatRoomRequest createRoomRequest) {
        return ResponseEntity.ok("Room Created :%s".formatted(createRoomRequest.getName()));
    }

    //Check if already in room
    //Otherwise add
    @PostMapping("{roomId}/join")
    public Mono<ResponseEntity> joinRoom(@PathVariable String roomId) {
        return null;
    }

    //Check if member of room
    //Otherwise error
    @DeleteMapping("{roomId}/leave")
    public Mono<ResponseEntity> leaveRoom(@PathVariable String roomId) {
        return null;
    }
}
