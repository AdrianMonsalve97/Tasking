package com.Amxx.Tasking.Security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.Amxx.Tasking.Security.Enums.RolNombre;
import com.Amxx.Tasking.Security.Models.Rol;
import com.Amxx.Tasking.Security.Repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }
}