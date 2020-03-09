package com.maint.maintjpa.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Materia extends EntidadBase {

    private String nombre;
    private String aula;
    private String numeroEstudiantes;
    private String anio;








}
