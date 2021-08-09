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
@RequestMapping("/")
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
}
