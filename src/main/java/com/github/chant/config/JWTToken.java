package com.github.chant.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @Author capricorn
 * @Description token
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
