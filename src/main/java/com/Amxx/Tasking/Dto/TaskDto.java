package com.Amxx.Tasking.Dto;

import java.util.Date;

public class TaskDto {
    private Long id;
    private String description;
    private Date fecha;
    private Long cantidad;
    private Boolean activo;
    UsuarioDto usuarioDto;

    public TaskDto(Long id, String description, Date fecha, Long cantidad, Boolean activo, UsuarioDto usuarioDto) {
        this.id = id;
        this.description = description;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.activo = activo;
        this.usuarioDto = usuarioDto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public TaskDto() {
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
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Date return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioDto getUsuario() {
        return usuarioDto;
    }

    public void setUsuario(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
