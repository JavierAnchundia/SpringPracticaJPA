package com.maint.maintjpa.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Materia extends EntidadBase {

    private String nombre;
    private int aula;
    private int numeroEstudiantes;
    private int anio;
   @ManyToOne(optional=false, cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private Persona persona;






}
