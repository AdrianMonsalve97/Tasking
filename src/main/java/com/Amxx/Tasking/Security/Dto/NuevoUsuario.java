package com.Amxx.Tasking.Security.Dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}