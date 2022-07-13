package ru.avbugorov.vaadinaw.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.avbugorov.vaadinaw.data.entity.Client;
import ru.avbugorov.vaadinaw.repositories.ClientRepository;

@SpringComponent
@UIScope
public class EmployeeEditor extends VerticalLayout {
    private ClientRepository clientRepository;
    private Client client;

    TextField firstName = new TextField("Name");
    TextField passport = new TextField("Passport");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());

    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    Binder<Client> binder = new Binder<>(Client.class);
//    private ChangeHandler changeHandler;
}
