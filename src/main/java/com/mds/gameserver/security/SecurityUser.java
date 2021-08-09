package com.mds.gameserver.security;

import com.mds.gameserver.database.data.SecurityUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String name;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String name, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
    public static UserDetails fromUser(SecurityUserDetail user) {
        return new User( user.getName(),user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities());
    }
}
