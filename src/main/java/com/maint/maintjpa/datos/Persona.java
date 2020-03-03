package com.maint.maintjpa.datos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Persona {
    @Id
    private Long id;

    private String nombre;
    private String apellido;

}
