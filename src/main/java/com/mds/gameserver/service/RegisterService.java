package com.mds.gameserver.service;

import com.mds.gameserver.config.SecurityConfiguration;
import com.mds.gameserver.database.data.SecurityUserDetail;
import com.mds.gameserver.database.repository.SecurityUserRepository;
import com.mds.gameserver.security.Role;
import com.mds.gameserver.security.Status;
import com.mds.gameserver.security.jwt.JwtTokenCloud;
import com.mds.gameserver.views.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private final SecurityUserRepository securityUserRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;
    @Autowired
    private JwtTokenCloud jwtTokenCloud;

    public RegisterService(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }
    public ResponseEntity register(RegisterInfo registerInfo){
        SecurityUserDetail user = securityUserRepository.findByName(registerInfo.name);
        if(user==null) {
            user = new SecurityUserDetail();
            user.setName(registerInfo.name);
            user.setPassword(securityConfiguration.passwordEncoder().encode(registerInfo.getPassword()));
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            securityUserRepository.save(user);
            return ResponseEntity.ok("good");
        }
        return (ResponseEntity) ResponseEntity.status(500);
    }
    public void logout(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        jwtTokenCloud.deleteTokenByUsername(name);
    }
}
