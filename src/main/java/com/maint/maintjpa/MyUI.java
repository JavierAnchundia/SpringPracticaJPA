package com.maint.maintjpa;

import com.maint.maintjpa.datos.MateriaRepositorio;
import com.maint.maintjpa.datos.PersonaRepositorio;
import com.maint.maintjpa.entidades.Materia;
import com.maint.maintjpa.entidades.Persona;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

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

    @Autowired(required = true)
    private MateriaRepositorio materiaRepositorio;
    private Grid<Persona> grid = new Grid<>(Persona.class);
    private MateriaGrid materiaGrid;
    private MateriasForm materiasForm;

    private TextField filterText = new TextField();
    private TextArea materiasPersona = new TextArea();
   // private CustomerService service = CustomerService.getInstance();
    private CustomerForm form;
    private HorizontalLayout UIMaterias;
   // private CustomerService serviiceñ;
    //private Persona p;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        UIMaterias= new HorizontalLayout();

        materiaGrid = new MateriaGrid(this,materiaRepositorio);
        materiasForm = new MateriasForm(this,materiaRepositorio);

        UIMaterias.addComponents(materiaGrid,materiasForm);
        UIMaterias.setVisible(false);
        materiaGrid.setVisible(false);


        Button agregarMaterias = new Button("Agregar  materias");
        Button visualizarCustomer = new Button("Visualizar Customers");
        Button viewMaterias = new Button("Visualizar las materias");


        visualizarCustomer.setVisible(false);

        form = new CustomerForm(this,personaRepositorio,materiaRepositorio);
        final VerticalLayout layout = new VerticalLayout();
        creacionMaterias();
        form.setVisible(false);
        System.out.println(materiaRepositorio);
        filterText.setPlaceholder("filter by name...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        materiasForm.setVisible(false);
        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Persona());

        });

        visualizarCustomer.addClickListener(e -> {
            UIMaterias.setVisible(false);
            grid.setVisible(true);
            materiaGrid.setVisible(false);
            agregarMaterias.setVisible(false);
            addCustomerBtn.setVisible(true);
            visualizarCustomer.setVisible(false);
            viewMaterias.setVisible(true);

        });

        viewMaterias.addClickListener(event -> {
           grid.setVisible(false);
           UIMaterias.setVisible(true);
           form.setVisible(false);
           materiaGrid.setVisible(true);
           agregarMaterias.setVisible(true);
           addCustomerBtn.setVisible(false);
           visualizarCustomer.setVisible(true);
           viewMaterias.setVisible(false);
        });


        agregarMaterias.addClickListener(event -> {
            materiaGrid.getGrid().asSingleSelect().clear();
            materiasForm.setMateria(new Materia());

        });
        agregarMaterias.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn,
                visualizarCustomer,viewMaterias,agregarMaterias);
        grid.setColumns("nombre", "apellido");
        materiaGrid.getGrid().setColumns("nombre","aula","numeroEstudiantes","anio");
       // materiaGrid.getGrid().addColumn((Materia::getNombrePersona)).setCaption("Name");


        HorizontalLayout main = new HorizontalLayout(grid, UIMaterias,form);
        main.setSizeFull();
        grid.setSizeFull();
        materiaGrid.getGrid().setSizeFull();
        UIMaterias.setSizeFull();
        materiaGrid.setSizeFull();

        UIMaterias.setExpandRatio(materiaGrid,1);
        materiaGrid.setExpandRatio(materiaGrid.getGrid(),1);
        main.setExpandRatio(grid, 1);


        layout.addComponents(toolbar, main , materiasPersona);
        updateList();
        updateListMaterias();

        setContent(layout);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {

                form.setCustomer(event.getValue());
                materiasPersona.setValue(updateTextArea(event.getValue()));
            }
        });

        materiaGrid.getGrid().asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                materiasForm.setVisible(false);
            } else {
                System.out.println(event.getValue().getNombre());

                materiasForm.setMateria(event.getValue());
            }        });

    }
    public String updateTextArea(Persona persona){
      List<Materia> materias = materiaRepositorio.findByPersona(persona);
      String texto="";
      for(Materia materia:materias){
          texto+= materia.getNombre()+ "\n";
      }

      return texto;
    }
    public void updateList() {
        System.out.println(filterText.getValue());
        List<Persona> customers;
        if(filterText.getValue().equals(""))
            {
              customers = personaRepositorio.findAll();
              System.out.println(customers);
            }
        else {
             customers = personaRepositorio.findByNombreStartingWith(filterText.getValue());
             }
        grid.setItems(customers);

    }

    public void updateListMaterias() {
        System.out.println(filterText.getValue());
        List<Materia> materias;
        if(filterText.getValue().equals(""))
        {
            materias = materiaRepositorio.findAll();
            System.out.println(materias);
        }
        else {
            materias = materiaRepositorio.findByNombreStartingWith(filterText.getValue());
        }
        materiaGrid.getGrid().setItems(materias);
    }
    public void creacionMaterias(){
        Materia matematicas = new Materia();
        matematicas.setAnio("2000");
        matematicas.setAula("20");
        matematicas.setNumeroEstudiantes("50");
        matematicas.setNombre("Matematicas");

        Materia bases = new Materia();
        bases.setAnio("2020");
        bases.setAula("29");
        bases.setNumeroEstudiantes("40");
        matematicas.setNombre("Bases de Datos");


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
