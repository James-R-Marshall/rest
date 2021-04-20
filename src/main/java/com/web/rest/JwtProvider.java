package com.web.rest;



import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.back.User;
import com.back.UserAuthenticator;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtProvider {
 
    private int jwtExpiration = 60;
 
    public String generateJwtToken(User user) {
              Key hmacKey = new SecretKeySpec("asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4".getBytes(), SignatureAlgorithm.HS256.getJcaName());
        String jwtToken = Jwts.builder()
                    .claim("Username", new String(user.getUsername()))
                    .claim("email", new String(user.getEmail()))
                    .setIssuedAt(new Date())
                    .setExpiration((new Date((new Date()).getTime() + jwtExpiration*1000)))
                    .signWith(hmacKey)
                    .compact();
                
        return jwtToken;
    }
    
    // public boolean validateJwtToken(String authToken) {
    //     Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    //     return ...;
    // }
    
    // public String getUserNameFromJwtToken(String token) {
    //     return Jwts.parser()
    //                   .setSigningKey(jwtSecret)
    //                   .parseClaimsJws(token)
    //                   .getBody().getSubject();
    // }
}