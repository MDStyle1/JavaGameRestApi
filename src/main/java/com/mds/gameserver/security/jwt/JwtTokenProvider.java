package com.mds.gameserver.security.jwt;

import com.mds.gameserver.security.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;
    @Autowired
    private JwtTokenCloud jwtTokenCloud;

    @Autowired
    private UserDetailsService userDetailsService;

    public String createToken(String username, Role roles){
        String firstToken = jwtTokenCloud.findTokenByUsername(username);
        if(firstToken!=null){
//            if(validateTokenByCreate(firstToken)){
//                return null;
//            }
            jwtTokenCloud.deleteTokenByUsername(username);
        }
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles",getRoleNames(roles));
        Date now = new Date();
        Date validity = new Date(now.getTime()+validityInMilliseconds);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        jwtTokenCloud.saveToken(username,token);
        return token;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        System.out.println("get Token");
        Cookie [] cookies = request.getCookies();
        String bearerToken = null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("JWT".equals(cookie.getName())) {
                    bearerToken = cookie.getValue();
                }
            }
        }
        if(bearerToken!=null&&bearerToken.startsWith("Bearer_")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

    public boolean validateTokenByCreate(String token){
        try{
            if(!jwtTokenCloud.findAndValidateTokenByUsername(getUsername(token),token)){
                return false;
            }
            Jws<Claims> claims =Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims =Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
//    private List<String> getRoleNames(List<Role> userRoles){
//        List<String> result = new ArrayList<>();
//        userRoles.forEach(role -> result.add(role.name()));
//        return result;
//    }
    private String getRoleNames(Role userRoles){
        return userRoles.name();
    }
}
