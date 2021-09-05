package com.mds.gameserver.security.jwt;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenCloud {
    private Map<String,String> listToken = new HashMap<String,String>();

    public void saveToken(String username,String token){
        listToken.put(username,token);
    }
    public boolean findAndValidateTokenByUsername(String username,String token){
        String tokenResult = listToken.get(username);
        if(tokenResult!=null&&tokenResult.equals(token)){
            return true;
        } return false;
    }
    public void deleteTokenByUsername(String username){
        listToken.remove(username);
    }
    public String findTokenByUsername(String username){
        return listToken.get(username);
    }
}
