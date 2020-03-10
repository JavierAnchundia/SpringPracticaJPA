package com.maint.maintjpa.datos;

import com.maint.maintjpa.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//Recuerda que esto es el Patron Repositorio implementado por el framework por debajo
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {


    List<Persona> findByNombreAndApellido(String nombre,String Apellido);
    List<Persona> findByNombre(String nombre);
    List<Persona> findByNombreStartingWith(String nombre);
}

