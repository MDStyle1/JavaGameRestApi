package com.mds.gameserver.controllers;

import com.mds.gameserver.page.MainMenuPageGet;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class MainMenuController {

    @GetMapping("menu")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity login(HttpServletRequest request){
        String app = request.getHeader("app");
        if(app==null){
            MainMenuPageGet mainMenuPageGet = new MainMenuPageGet();
            return ResponseEntity.ok(mainMenuPageGet.getPage());
        }
        return ResponseEntity.ok("is app");
    }
}
