package com.Amxx.Tasking.Security.Repository;

import java.util.Optional;

import com.Amxx.Tasking.Security.Enums.RolNombre;
import com.Amxx.Tasking.Security.Models.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
