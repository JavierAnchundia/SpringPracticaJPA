package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class MateriasForm extends FormLayout {

    private TextField nombre = new TextField("nombre");
    private TextField aula = new TextField("aula");
    private TextField numeroEstudiantes = new TextField("#Estudiantes");
    private TextField anio = new TextField("anio");

    private MyUI myUI;
    private Materia materia;
    private MateriaRepositorio materiaRepositorio;
    private Binder<Materia> binderM = new Binder<>(Materia.class);

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    public MateriasForm(MyUI myUI, MateriaRepositorio materiaRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
        this.myUI = myUI;
        binderM.bindInstanceFields(this);

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(nombre,aula,numeroEstudiantes,anio , buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
        binderM.setBean(this.materia);

        // Show delete button for only customers already in the database
        delete.setVisible(this.materia.isPersisted());
        setVisible(true);
    }

    private void delete() {

        materiaRepositorio.delete(materia);
        myUI.updateListMaterias();
        setVisible(false);
    }

    private void save() {

        materiaRepositorio.save(materia);
        myUI.updateListMaterias();
        setVisible(false);
    }



}
