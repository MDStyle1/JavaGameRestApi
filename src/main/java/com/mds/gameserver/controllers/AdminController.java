package com.mds.gameserver.controllers;

import com.mds.gameserver.service.AdminService;
import com.mds.gameserver.views.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/banned")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity banned(@RequestBody UserName userName){
        System.out.println(userName.name);
        return ResponseEntity.ok(adminService.banned(userName.name));
    }
}
