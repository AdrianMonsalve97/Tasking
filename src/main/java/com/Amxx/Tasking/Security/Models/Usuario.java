package com.Amxx.Tasking.Security.Models;

import javax.validation.constraints.NotNull;

import com.Amxx.Tasking.Models.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String telefono;
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "usuario")
    List<Task> task;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

 


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Rol> getRoles() {
        return roles;
    }


    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }


    public Usuario() {
    }


    public Usuario(Long id, @NotNull String nombre, @NotNull String telefono, @NotNull String nickname,
            List<Task> task) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nickname = nickname;
        this.task = task;
    }


    public Usuario(String nombre2, String nombreUsuario, String encode) {
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

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

   

}
