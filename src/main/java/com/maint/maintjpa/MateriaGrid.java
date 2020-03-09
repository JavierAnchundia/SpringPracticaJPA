package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaGrid extends VerticalLayout {
    @Autowired(required = true)
    private MateriaRepositorio materiaRepositorio;

    private MyUI myUI;
    private Grid<Materia> grid = new Grid<>(Materia.class);

    public MateriaGrid(MyUI myUI, MateriaRepositorio materiaRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
        this.myUI = myUI;

        setSizeUndefined();
        addComponents(grid);

    }

    public Grid<Materia> getGrid() {
        return grid;
    }

    public void setGrid(Grid<Materia> grid) {
        this.grid = grid;
    }
}
