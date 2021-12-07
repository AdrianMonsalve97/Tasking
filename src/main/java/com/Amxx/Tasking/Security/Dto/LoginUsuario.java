package com.Amxx.Tasking.Security.Dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;

    public String getNombreUsuario() {
        return nickname;
    }

    public void setNombreUsuario(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}