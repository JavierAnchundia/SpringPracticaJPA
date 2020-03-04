package com.maint.maintjpa;

import com.maint.maintjpa.datos.Persona;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {

    @Autowired(required = true)
    private PersonaRepositorio personaRepositorio;
    private TextField filterText = new TextField();
   // private CustomerService service = CustomerService.getInstance();
    private Grid<Persona> grid = new Grid<>(Persona.class);
    private CustomerForm form = new CustomerForm(this,personaRepositorio);
   // private CustomerService serviiceñ;
    //private Persona p;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("filter by name...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        grid.setColumns("nombre", "apellido");

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(filtering, main);        updateList();

        setContent(layout);
    }

    public void updateList() {
        System.out.println(filterText.getValue());
        List<Persona> customers;
        if(filterText.getValue().equals(""))
            {
              customers = personaRepositorio.findAll();
            }
        else {
             customers = personaRepositorio.findByNombreStartingWith(filterText.getValue());
             }
        grid.setItems(customers);
    }

    @Override
    public void showView(View view) {

    }
    /*
    public void updateList() {
        // fetch list of Customers from service and assign it to Grid
        List<Customer> customers = service.findAll(filterText.getValue());
        grid.setItems(customers);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }*/
}
