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

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
