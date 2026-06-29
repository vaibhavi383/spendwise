package com.vaibhavi.spendwise.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavi.spendwise.dto.LoginRequest;
import com.vaibhavi.spendwise.dto.LoginResponse;
import com.vaibhavi.spendwise.entity.User;
import com.vaibhavi.spendwise.security.CustomUserDetailsService;
import com.vaibhavi.spendwise.security.JwtUtil;
import com.vaibhavi.spendwise.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

    	try {

    	    authenticationManager.authenticate(

    	            new UsernamePasswordAuthenticationToken(
    	                    request.getEmail(),
    	                    request.getPassword()));

    	} catch (Exception e) {

    	    e.printStackTrace();

    	    throw e;
    	}

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getEmail());

        String token =
                jwtUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }
    
    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {

        return userService.registerUser(user);
    }
}