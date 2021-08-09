package com.mds.gameserver.service;

import com.mds.gameserver.database.data.SecurityUserDetail;
import com.mds.gameserver.database.repository.SecurityUserRepository;
import com.mds.gameserver.security.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private final SecurityUserRepository securityUserRepository;

    public AdminService(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }
    public String banned(String name){
        SecurityUserDetail securityUserDetail = securityUserRepository.findByName(name);
        if(securityUserDetail==null){
            return "not found user";
        }
        if(securityUserDetail.getStatus()==Status.ACTIVE){
            securityUserDetail.setStatus(Status.BANNED);
        }else securityUserDetail.setStatus(Status.ACTIVE);
        securityUserRepository.save(securityUserDetail);
        return "ok";
    }
}
