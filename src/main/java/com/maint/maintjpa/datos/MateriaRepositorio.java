package com.maint.maintjpa.datos;

import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaRepositorio extends JpaRepository<Materia, Long> {
    List<Materia> findByNombre(String nombre);
    List<Materia> findByNombreStartingWith(String nombre);
    List<Materia> findByPersona(Persona persona);

   /* @Query (value = "SELECT PERSONA_ID FROM MATERIA m ")
    List<> findPersona();*/
}
