package com.example.userservice.controller;

import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.dto.ResponseObject;
import com.example.userservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(AuthenticationController.BASE_URL)
public class AuthenticationController {
    public static final String BASE_URL = "/api/v1/auth";

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<AuthenticationResponse>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        ResponseObject<AuthenticationResponse> responseObject = new ResponseObject<AuthenticationResponse>();
        try {
            AuthenticationResponse response = this.authenticationService.createAuthenticationToken(authenticationRequest);
            responseObject.setData(Collections.singletonList(response));
            responseObject.setValid(true);
            responseObject.setMessage("Login Successfully");
        } catch (Exception e) {
            responseObject.setValid(false);
            responseObject.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(responseObject);
    }

}
