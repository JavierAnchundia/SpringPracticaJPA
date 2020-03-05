package com.maint.maintjpa.datos;

import com.maint.maintjpa.entidades.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepositorio extends JpaRepository<Materia, Long> {

}
