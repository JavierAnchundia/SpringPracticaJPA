package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ConvertidorPersona  implements Converter<String, Persona> {

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Override
    public Result<Persona> convertToModel (String nombre, ValueContext context)  {
        try {
            // ok is a static helper method that creates a Result
            return Result.ok(new Persona(nombre));
        } catch (NumberFormatException e) {
            // error is a static helper method that creates a Result
            return Result.error("Please enter a number");
        }
    }

    @Override
    public String convertToPresentation(Persona persona, ValueContext context) {

        return persona.getNombre()==null? "": persona.getNombre();
    }

    public Class<Persona> getModelType() {
        return Persona.class;
    }

    public Class<String> getPresentationType() {
        return String.class;
    }
}
