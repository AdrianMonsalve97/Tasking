package com.Amxx.Tasking.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.Amxx.Tasking.Dto.UsuarioDto;
import com.Amxx.Tasking.Security.Models.Usuario;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Long cantidad;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Boolean activo;
    @ManyToOne
    Usuario usuario;

    public Task(String description, Long cantidad, Date fecha, Boolean activo, Usuario usuario) {
        this.description = description;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.activo = activo;
        this.usuario = usuario;
    }

    public Task(String description, Long cantidad, Date fecha, Usuario usuario) {

        this.description = description;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Task() {

    }

    public Task(String description, Long id, Date fecha, UsuarioDto usuario, Long cantidad) {
        this.description = description;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Task(String description, Date fecha, Long cantidad) {
    }

    public Task(String description, Long id, Date fecha) {
        this.description = description;
        this.fecha = fecha;
    }

    public Task(String nombre, Long id, List<Task> task, String nickname, float f) {
    }

    // @Override
    // public String toString() {
    // return "Task [description=" + description + ", fecha=" + fecha + ", id=" + id
    // + ", usuario=" + usuario + "]";
    // }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Producto 👍{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description);
        sb.append(", cantidad='").append(cantidad);
        sb.append(", fecha=").append(fecha);

        return sb.toString();
    }

    public void aumentarCantidad() {
        this.setCantidad(this.getCantidad() + 1);
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

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return Boolean return the activo
     */
    public Boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
