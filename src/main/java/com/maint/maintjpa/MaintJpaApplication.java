package com.maint.maintjpa;

import com.maint.maintjpa.entidades.Materia;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(value = "Ruta del paquete en donde implementada esa interfaz")
@SpringBootApplication
public class MaintJpaApplication implements CommandLineRunner {

    //Autowired es que crea la clase sin tener que hacerle el "new Clase(...), asi como personaRepositorio


    //Generalmente se definen primero las constantes y variables
    public static void main(String[] args) {

        SpringApplication.run(MaintJpaApplication.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
    }
}
