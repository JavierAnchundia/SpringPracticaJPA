package com.maint.maintjpa;
import com.maint.maintjpa.datos.Persona;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerForm extends FormLayout {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");


    private PersonaRepositorio personaRepositorio;
    private Persona persona;
    private MyUI myUI;
    private Binder<Persona> binder = new Binder<>(Persona.class);

    public CustomerForm(MyUI myUI, PersonaRepositorio personaRepositorio) {
        this.myUI = myUI;
        binder.bindInstanceFields(this);

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(firstName, lastName, buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    }

    public void setCustomer(Persona persona) {
        this.persona = persona;
        binder.setBean(persona);

        // Show delete button for only customers already in the database
        delete.setVisible(persona.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }

}
