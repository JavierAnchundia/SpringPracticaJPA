package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;

public class ConvertidorMateria implements Converter<String, Materia>  {

    @Autowired
    MateriaRepositorio materiaRepositorio;

    @Override
    public Result<Materia> convertToModel (String nombre, ValueContext context)  {
        try {
            // ok is a static helper method that creates a Result
            return Result.ok(materiaRepositorio.findByNombre(nombre).get(0));
        } catch (NumberFormatException e) {
            // error is a static helper method that creates a Result
            return Result.error("Please enter a number");
        }
    }

    @Override
    public String convertToPresentation(Materia materia, ValueContext context) {
        return materia.getNombre();
    }

    public Class<Materia> getModelType() {
        return Materia.class;
    }

    public Class<String> getPresentationType() {
        return String.class;
    }
}

