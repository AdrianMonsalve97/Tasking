package com.Amxx.Tasking.Dto;

public class UsuarioDto {

    private Long id;
    private String nombre;
    private String telefono;
    private String nickname;
    private String password;

    TaskDto taskDto;

    public UsuarioDto(Long id, String nombre, String telefono, String nickname, String password, TaskDto taskDto) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nickname = nickname;
        this.password = password;
        this.taskDto = taskDto;
    }

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }

}
