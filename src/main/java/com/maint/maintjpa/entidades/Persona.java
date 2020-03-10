package com.maint.maintjpa.entidades;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Persona extends EntidadBase {

    private String nombre;
    private String apellido;
    private String cedula;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Materia> materias;

    public Persona(){
    }

    public Persona(String nombre)
    {
        this.setNombre(nombre);
    }

    public void agregarMateria(Materia materia){
        materia.setPersona(this);

        List<Materia> temp = new ArrayList<>();
        temp.add(materia);
        temp.addAll(this.getMaterias());

        this.setMaterias(temp);

    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
