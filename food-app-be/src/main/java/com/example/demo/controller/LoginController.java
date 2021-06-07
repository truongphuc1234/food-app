package com.example.demo.controller;


import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.entity.Account;
import com.example.demo.model.entity.AccountRole;
import com.example.demo.security.JwtRequest;
import com.example.demo.security.JwtResponse;
import com.example.demo.service.AccountRoleService;
import com.example.demo.service.AccountService;
import com.example.demo.service.impl.JwtAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {


    private AuthenticationManager authenticationManager;
    private JwtAccountDetailService jwtAccountDetailService;
    private JwtTokenUtil jwtTokenUtil;
    private AccountRoleService accountRoleService;
    private AccountService accountService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtAccountDetailService(JwtAccountDetailService jwtAccountDetailService) {
        this.jwtAccountDetailService = jwtAccountDetailService;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        try {
            JwtResponse jwtResponse = login(jwtRequest);
            return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private JwtResponse login(JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getAccountName(), jwtRequest.getPassword());
        } catch (Exception e) {
            if (e.getMessage().equals("INVALID_CREDENTIALS")) {
                return new JwtResponse(e.getMessage());
            }
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        String token = null;
        if (userDetails.isEnabled()) {
            token = jwtTokenUtil.generateToken(userDetails);
        }
        JwtResponse jwtResponse = new JwtResponse(token);
        Account account = accountService.findByAccountName(jwtRequest.getAccountName());
        jwtResponse.setAccount(account);
        List<String> roleList = new ArrayList<>();
        for (AccountRole accountRole : accountRoleService.findAllByAccountId(account.getAccountId())) {
            roleList.add(accountRole.getRole().getRoleName());
        }
        jwtResponse.setRoles(roleList);
        return jwtResponse;
    }

    private void authenticate(String accountName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}