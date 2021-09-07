package com.mds.gameserver.controllers;

import com.mds.gameserver.database.data.SecurityUserDetail;
import com.mds.gameserver.database.repository.SecurityUserRepository;
import com.mds.gameserver.page.LoginPageGet;
import com.mds.gameserver.security.jwt.JwtTokenProvider;
import com.mds.gameserver.service.RegisterService;
import com.mds.gameserver.views.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/**")
@CrossOrigin(origins = "http://188.243.224.61:8080")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private SecurityUserRepository repository;

    @Autowired
    public RegisterController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("register")
    public ResponseEntity register(){
        return ResponseEntity.ok("ok reg");
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterInfo registerInfo){
        return registerService.register(registerInfo);
    }

    @GetMapping("login")
    public ResponseEntity login(HttpServletRequest request){
        String app = request.getHeader("app");
        if(app==null){
            LoginPageGet loginPageGet = new LoginPageGet();
            return ResponseEntity.ok(loginPageGet.getPage());
        }
        return ResponseEntity.ok("is app");
    }
    @GetMapping("out")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity logout(HttpServletRequest request){
        String app = request.getHeader("app");
        if(app==null){
            registerService.logout();
            return ResponseEntity.ok("");
        }
        return ResponseEntity.ok("is app");
    }

    @PostMapping("login")
//    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity login(@RequestBody RegisterInfo registerInfo, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = registerInfo.getName();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,registerInfo.getPassword()));
        SecurityUserDetail securityUserDetail = repository.findByName(username);
        if(securityUserDetail==null){
            throw new UsernameNotFoundException("User not found "+username);
        }
        String token = jwtTokenProvider.createToken(username,securityUserDetail.getRole());
        if(token==null){
            return (ResponseEntity) ResponseEntity.badRequest();
        }
        String app = request.getHeader("app");
        if(app==null){
            Cookie cookie = new Cookie("JWT","Bearer_"+token);
            cookie.setPath("/");
            cookie.setMaxAge(999);
            response.addCookie(cookie);
            response.setContentType("text/plain");
            return ResponseEntity.ok("");
        }
        Map<Object ,Object> res = new HashMap<>();
        res.put("username",username);
        res.put("token",token);
        System.out.println("Login");
        return ResponseEntity.ok(res);
    }
//    @GetMapping("**")
//    public ResponseEntity notFound(){
//        return ResponseEntity.ok("<!DOCTYPE html>\n" +
//                "<html lang=\"ru\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>notFound</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h4 style=\"text-align: center;\"><strong>Доступные комманды</strong></h4>\n" +
//                "<h4 style=\"text-align: center;\"><strong>/register</strong></h4>\n" +
//                "<h4 style=\"text-align: center;\"><strong>/login</strong></h4>\n" +
//                "<h4 style=\"text-align: center;\"><strong>/scores/newscore</strong></h4>\n" +
//                "<h4 style=\"text-align: center;\"><strong>/scores/top10</strong></h4>\n" +
//                "<h4 style=\"text-align: center;\"><strong>/scores/del</strong></h4>\n" +
//                "</body>\n" +
//                "</html>");
////                ResponseEntity.ok("не существует");
//    }
}
