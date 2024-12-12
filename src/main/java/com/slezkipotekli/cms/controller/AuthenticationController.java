package com.slezkipotekli.cms.controller;

import com.slezkipotekli.cms.service.AuthenticationService;
import com.slezkipotekli.cms.dto.auth.AuthenticationRequest;
import com.slezkipotekli.cms.dto.auth.AuthenticationResponse;
import com.slezkipotekli.cms.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vregi, 11/24/2024
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( // registration endpoint
                                                            @RequestBody RegisterRequest request
    ){
        log.info("User was registered with login: {}", request.getEmail());
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate( // authentication endpoint
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}

