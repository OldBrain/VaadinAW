package ru.avbugorov.vaadinaw.list;

import ch.qos.logback.core.Layout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import ru.avbugorov.vaadinaw.dto.ClientViewDto;
import ru.avbugorov.vaadinaw.services.ClientsService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("")
//@RequiredArgsConstructor
public class MainView extends VerticalLayout {

    //    private final ClientRepository clientRepository;
    private final ClientsService clientsService;
    //    private EmployeeEditor editor;
    Grid<ClientViewDto> grid;
    TextField filterText;
    private Button addNewBtn;


    @Autowired
    public MainView(ClientsService clientsService) {
        super();
        this.clientsService = clientsService;
        init();
    }

    private void init() {

        // final VerticalLayout vLayout = new VerticalLayout();
        //   grid.setColumns("id", "name", "passport","address","phone");
        this.grid = new Grid<>(ClientViewDto.class);
        this.filterText = new TextField();
        List<ClientViewDto> list = clientsService.findAll();
        this.addNewBtn = new Button("New employee", VaadinIcon.PLUS.create());
        // HorizontalLayout actions = new HorizontalLayout(filterText, addNewBtn);
        add(filterText);
        add(grid);


        //   grid.setItems(this.clientsService.findAll());
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        filterText.setPlaceholder("Filter by last name");
        filterText.addValueChangeListener(e -> updateList(list, filterText.getValue()));
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        // final VerticalLayout filterLayout = new VerticalLayout();


    }

    private void updateList(List<ClientViewDto> list, String fText) {
        grid.setItems(list.stream()
                .filter(clientViewDto -> clientViewDto.getName().contains(fText))
                .collect(Collectors.toList()));
    }

//    public MainView() {
////        this.clientRepository = clientRepository;
//
////        add(actions, grid, editor);
////        add(actions, grid);
//
////        System.out.println(this.clientsService.findAll().toString());
//
////        grid.setHeight("200px");
////        grid.setColumns("id", "name", "passport","address","phone");
////        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
//
//
//
//    }
}
