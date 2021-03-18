package com.web.rest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.Cookie;

import com.back.User;
import com.back.UserAuthenticator;
import com.back.UserData;
import com.back.tolken;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController

    public class LoginController {
    
    @GetMapping("/login")
    public Cookie listProducts(@RequestParam(value = "username", defaultValue = "0") String username, @RequestParam(value = "password", defaultValue = "1") String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        UserData init = new UserData();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        UserAuthenticator ua = new UserAuthenticator();
        var usr = ua.authenticate(username, password, init);
        JwtProvider jp = new JwtProvider();
        tolken t = new tolken();
        t.token= jp.generateJwtToken(usr);
        Cookie c = new Cookie("session", t.token);
        c.setHttpOnly(true);
        c.setSecure(true);
        return c;

    }
}
