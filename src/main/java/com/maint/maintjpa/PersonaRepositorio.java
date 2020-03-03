package com.maint.maintjpa;

import com.maint.maintjpa.datos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Recuerda que esto es el Patron Repositorio implementado por el framework por debajo
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {


}
