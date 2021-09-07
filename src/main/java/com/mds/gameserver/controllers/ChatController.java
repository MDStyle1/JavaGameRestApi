package com.mds.gameserver.controllers;

import com.mds.gameserver.chat.ChatListenService;
import com.mds.gameserver.chat.ChatSendMessage;
import com.mds.gameserver.chat.RequestChat;
import com.mds.gameserver.page.ChatPageGet;
import com.mds.gameserver.page.LoginPageGet;
import com.mds.gameserver.page.TestPageGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatListenService chatListenService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity chat(HttpServletRequest request){
        String app = request.getHeader("app");
        if(app==null){
            ChatPageGet chatPageGet = new ChatPageGet();
            return ResponseEntity.ok(chatPageGet.getPage());
        }
        return ResponseEntity.ok("is app");
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity test(HttpServletRequest request){
        String app = request.getHeader("app");
        if(app==null){
            TestPageGet testPageGet = new TestPageGet();
            return ResponseEntity.ok(testPageGet.getPage());
        }
        return ResponseEntity.ok("is app");
    }

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
