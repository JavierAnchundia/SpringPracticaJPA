package com.maint.maintjpa;

import com.maint.maintjpa.datos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

//Recuerda que esto es el Patron Repositorio implementado por el framework por debajo
@Component
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {


    List<Persona> findByNombreAndApellido(String nombre,String Apellido);
    List<Persona> findByNombre(String nombre);
    List<Persona> findByNombreStartingWith(String nombre);


}

