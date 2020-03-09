package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionException;

import java.util.Arrays;
import java.util.List;

public class ConvertidorMateria implements Converter<String, List<Materia>>  {

    @Autowired
    MateriaRepositorio materiaRepositorio;

    @Override
    public Result<List<Materia>> convertToModel (String nombre, ValueContext context)  {
        try {
            // ok is a static helper method that creates a Result
            return Result.ok(Arrays.asList(new Materia()));
        } catch (NumberFormatException e) {
            // error is a static helper method that creates a Result
            return Result.error("Please enter a number");
        }
    }

    @Override
    public String convertToPresentation(List<Materia> materias, ValueContext context)
    {
        String smaterias="";
        if(materias==null) return smaterias;
        for(int i=0;i<materias.size();i++){
            smaterias+=materias.get(i).getNombre();
            smaterias+="\n";
        }
        return smaterias;
    }

    public Class<Materia> getModelType() {
        return Materia.class;
    }

    public Class<String> getPresentationType() {
        return String.class;
    }
}

