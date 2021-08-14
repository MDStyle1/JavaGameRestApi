package com.mds.gameserver.controllers;

import com.mds.gameserver.service.RegisterService;
import com.mds.gameserver.views.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/**")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @GetMapping("register")
    public ResponseEntity register(){
        return ResponseEntity.ok("ok reg");
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterInfo registerInfo){
        return ResponseEntity.ok(registerService.register(registerInfo));
    }
    @GetMapping("login")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity login() {
        return ResponseEntity.ok("Its ok");
    }
    @GetMapping("**")
    public ResponseEntity notFound(){
        return ResponseEntity.ok("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>notFound</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4 style=\"text-align: center;\"><strong>Доступные комманды</strong></h4>\n" +
                "<h4 style=\"text-align: center;\"><strong>/register</strong></h4>\n" +
                "<h4 style=\"text-align: center;\"><strong>/login</strong></h4>\n" +
                "<h4 style=\"text-align: center;\"><strong>/scores/newscore</strong></h4>\n" +
                "<h4 style=\"text-align: center;\"><strong>/scores/top10</strong></h4>\n" +
                "<h4 style=\"text-align: center;\"><strong>/scores/del</strong></h4>\n" +
                "</body>\n" +
                "</html>");
//                ResponseEntity.ok("не существует");
    }
}
