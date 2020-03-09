package com.maint.maintjpa;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {

    private TextField nombre = new TextField("Nombre");
    private TextField apellido = new TextField("Apellido");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private TextArea materias = new TextArea("Materias");
    private NativeSelect<Materia> status = new NativeSelect<>("Materias");




    private PersonaRepositorio personaRepositorio;
    private Persona persona;
    private MyUI myUI;
    private Binder<Persona> binder = new Binder<>(Persona.class);

    public CustomerForm(MyUI myUI, PersonaRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
        this.myUI = myUI;


        binder.forField(materias)
                .withConverter(new ConvertidorMateria())
                .bind(Persona::getMaterias,Persona::setMaterias);
        binder.bindInstanceFields(this);

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(nombre, apellido, status, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }

    public void setCustomer(Persona persona) {
        this.persona = persona;


        binder.forField(materias)
                .withConverter(new ConvertidorMateria())
                .bind(Persona::getMaterias,Persona::setMaterias);

        binder.setBean(this.persona);

        // Show delete button for only customers already in the database
        delete.setVisible(this.persona.isPersisted());
        setVisible(true);
        nombre.selectAll();
    }

    private void delete() {

        personaRepositorio.delete(persona);
        myUI.updateList();
        setVisible(false);
    }

    private void save() {
        personaRepositorio.save(persona);
        myUI.updateList();
        setVisible(false);
    }

}
