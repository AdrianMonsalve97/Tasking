package com.Amxx.Tasking.Security.Dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nickname, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * @return String return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}