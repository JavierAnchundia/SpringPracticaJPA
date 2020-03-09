package com.maint.maintjpa.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Persona extends EntidadBase {

    private String nombre;
    private String apellido;
    private String cedula;

}
