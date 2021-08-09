package com.mds.gameserver.service;

import com.mds.gameserver.database.data.SecurityUserDetail;
import com.mds.gameserver.database.repository.SecurityUserRepository;
import com.mds.gameserver.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    private SecurityUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDetail user = repository.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return SecurityUser.fromUser(user);
    }
}
