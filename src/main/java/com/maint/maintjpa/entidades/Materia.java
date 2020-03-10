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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Persona persona;

    public Materia()
    {
        /*this.setPersona(new Persona());
        persona.setNombre("");*/

    }

    public Materia(Persona p)
    {
        this.setPersona(p);
        persona.setNombre("");

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString(){
        return this.getNombre();
    }
}


