package com.mds.gameserver.controllers;

import com.mds.gameserver.chat.ChatListenService;
import com.mds.gameserver.chat.ChatSendMessage;
import com.mds.gameserver.chat.RequestChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatListenService chatListenService;

    @PostMapping("/listen")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity listen(@RequestBody RequestChat requestChat){
        return chatListenService.addListenUser(requestChat);
    }
    @PostMapping("/send")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity send(@RequestBody ChatSendMessage chatSendMessage){
        chatListenService.addMessage(chatSendMessage);
        return ResponseEntity.ok("");
    }
    @GetMapping("/clear")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity clear(){
        chatListenService.clearChat();
        return ResponseEntity.ok("");
    }
}
