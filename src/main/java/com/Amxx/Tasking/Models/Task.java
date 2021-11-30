package com.Amxx.Tasking.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long cantidad;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    @ManyToOne
    Usuario usuario;

    public Task() {
    }

    public Task(String description2, Long id2) {
    }

    public Task(String description2, Long id2, Date fecha2, Usuario usuario2) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCantidad() {
        return cantidad;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Task [description=" + description + ", fecha=" + fecha + ", id=" + id + ", usuario=" + usuario + "]";
    }

    public void aumentarCantidad() {
        this.setCantidad(this.getCantidad() + 1);
    }

}
