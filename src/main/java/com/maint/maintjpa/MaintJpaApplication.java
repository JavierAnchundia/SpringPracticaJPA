package com.maint.maintjpa;

import com.maint.maintjpa.datos.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(value = "Ruta del paquete en donde implementada esa interfaz")
@SpringBootApplication
public class MaintJpaApplication implements CommandLineRunner {

    //Autowired es que crea la clase sin tener que hacerle el "new Clase(...), asi como personaRepositorio
    @Autowired(required = true)
    PersonaRepositorio personaRepositorio;

    //Generalmente se definen primero las constantes y variables
    public static void main(String[] args) {

        SpringApplication.run(MaintJpaApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        Persona persona = new  Persona();
        persona.setId(new Long(1));
        persona.setNombre("Javier");
        persona.setApellido("Anchundia");
        personaRepositorio.save(persona);
    }
}
