package com.example.demo.security;

public class SocialResponse {
    private String jwtToken;

    public SocialResponse() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}