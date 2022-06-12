package com.example.userservice.service.impl;

import com.example.userservice.dao.UserDao;
import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.entity.User;
import com.example.userservice.exceptions.NotFoundException;
import com.example.userservice.jwt.JwtUtils;
import com.example.userservice.service.AuthenticationService;
import com.example.userservice.service.UserService;
import com.example.userservice.webConfig.CustomDetail;
import com.example.userservice.webConfig.CustomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomDetailService userDetailsService;

    @Override
    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        this.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final CustomDetail userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public Optional<User> getCurrentUser() {
        CustomDetail userDetail = (CustomDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetail.getUsername();
        Optional<User> user = userDao.findByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for EMAIL value " + email);
        }

        return Optional.of(user.get());
    }
}
