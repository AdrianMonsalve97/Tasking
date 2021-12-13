package com.Amxx.Tasking.Respositories;

import java.util.List;
import java.util.Optional;

import com.Amxx.Tasking.Security.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  // Valida si existe un usuario por nombre
  boolean existsByNombre(String nombre);

  // Valida si existe un usuario por nickname
  boolean existsByNickname(String nickname);

  boolean existsById(Long id);

  // Realiza un listado por nombre
  // List<Usuario> findByNombre(String nombre);
  // Realiza una busqueda por id del usuario
  boolean existsByNickname(Long id);

  @Query("SELECT u FROM Usuario u WHERE u.task IS NOT EMPTY ")
  List<Usuario> findByTaskContains();

  @Query("select u from Usuario u where u.nickname=?1")
  public Optional<Usuario> getByNickname(String nickname);

Usuario findFirstById(Long id);

  // @Query("select u.task from Usuario where = :id")
  // List<Usuario> findByIdContains(Long id);

}
