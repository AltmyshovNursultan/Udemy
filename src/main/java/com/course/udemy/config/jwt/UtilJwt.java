package com.course.udemy.config.jwt;

import com.course.udemy.config.daoconfig.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UtilJwt {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrinciple.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS512,secretKey.getBytes())
                .compact();
    }

    public boolean validateToken (String token){
    try{
        Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
        return true;
        }catch (MalformedJwtException e){
            return false;
        }
    }
    public String getEmailFromToken(String token){
        String email = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return token;
    }
}
