package org.vaadin.example;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dialog.Dialog;
import org.vaadin.example.model.ContactInfoModel;
import org.vaadin.example.providers.ContactDataProvider;
import org.vaadin.example.repository.ContactServiceDBImpl;
import org.vaadin.example.service.ContactInfoService;

import java.util.Arrays;
import java.util.List;

/**
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("crud-open-editor")
public class MainView extends Div {
    private Crud<ContactInfoModel> crud;

    private String FIRST_NAME = "firstName";
    private String LAST_NAME = "lastName";
    private String EMAIL = "email";

    private ContactInfoModel contactInfoModel = new ContactInfoModel();

    private ContactInfoService service = new ContactServiceDBImpl();


    public MainView() {
        crud = new Crud<>(ContactInfoModel.class, createEditor());

        setupGrid();
        setupDataProvider();

        add(crud);
    }

    private CrudEditor<ContactInfoModel> createEditor() {
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        EmailField email = new EmailField("Email");
        TextField phoneNumber = new TextField("Phone Number");
        phoneNumber.setRequiredIndicatorVisible(true);
        phoneNumber.setPattern("^[0-9]{4}?[0-9]{7}$");
        phoneNumber.setAllowedCharPattern("[0-9]");
        phoneNumber.setMinLength(11);
        phoneNumber.setMaxLength(11);

        TextField street = new TextField("Street");
        TextField city = new TextField("City");
        TextField country = new TextField("Country");

        Dialog dialog = new Dialog(new Span("Record is being changed"));


        FormLayout form = new FormLayout(firstName, lastName,
                email, phoneNumber, street, city, country);

        Binder<ContactInfoModel> binder = new Binder<>(ContactInfoModel.class);
        binder.forField(firstName).asRequired().bind(ContactInfoModel::getFirstName,
                ContactInfoModel::setFirstName);
        binder.forField(lastName).asRequired().bind(ContactInfoModel::getLastName,
                ContactInfoModel::setLastName);
        binder.forField(email).asRequired().bind(ContactInfoModel::getEmail,
                ContactInfoModel::setEmail);
        binder.forField(phoneNumber).withValidator(number -> isValidNumber(number),
                "phone number already exist")
                .bind(ContactInfoModel::getPhoneNumber, ContactInfoModel::setPhoneNumber);

        binder.forField(street).asRequired().bind(ContactInfoModel::getStreet,
                ContactInfoModel::setStreet);
        binder.forField(city).asRequired().bind(ContactInfoModel::getCity,
                ContactInfoModel::setCity);
        binder.forField(country).asRequired().bind(ContactInfoModel::getCountry,
                ContactInfoModel::setCountry);

        return new BinderCrudEditor<>(binder, form);
    }

    private boolean isValidNumber(String number) {
        return number.length() == 11
                && !service.isExistingPhoneNumber(number, contactInfoModel);
    }

    public Notification getWarning(){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_WARNING);

        Div text = new Div(
                new Text("Your session will expire in 5 minutes due to inactivity."),
                new HtmlComponent("br"),
                new Text("Close this warning to continue working.")
        );

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.setAriaLabel("Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);

        return notification;
    }

    private void setupGrid() {
        Grid<ContactInfoModel> grid = crud.getGrid();

        // Remove edit column
        Crud.removeEditColumn(grid);
        // grid.removeColumnByKey(EDIT_COLUMN);
        // grid.removeColumn(grid.getColumnByKey(EDIT_COLUMN));


        // Open editor on double click
        grid.addItemDoubleClickListener(event ->{
                contactInfoModel = event.getItem();
                checkConcurrency(event.getItem());
                crud.edit(event.getItem(), Crud.EditMode.EXISTING_ITEM);
                crud.getDataProvider().refreshAll();


        });

        // Only show these columns (all columns shown by default):
        List<String> visibleColumns = Arrays.asList(FIRST_NAME, LAST_NAME,
                EMAIL);
        grid.getColumns().forEach(column -> {
            String key = column.getKey();
            if (!visibleColumns.contains(key)) {
                grid.removeColumn(column);
            }
        });

        // Reorder the columns (alphabetical by default)
        grid.setColumnOrder(grid.getColumnByKey(FIRST_NAME),
                grid.getColumnByKey(LAST_NAME), grid.getColumnByKey(EMAIL));
    }
    private void checkConcurrency(ContactInfoModel item) {
        if(item.getEditMode()) {
            showWarning("This record is being modified by another user");
            item.setEditCount(item.getEditCount()+1);
        }
        else {
                item.setEditMode(true);
                item.setEditCount(item.getEditCount()+1);
        }
    }
    public void showWarning(String message){
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_WARNING);

        Div text = new Div(
                new Text(message),
                new HtmlComponent("br"),
                new Text("Close this notification to continue working.")
        );

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.setAriaLabel("Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }
    private void setupDataProvider() {
        ContactDataProvider dataProvider = new ContactDataProvider();
        crud.setDataProvider(dataProvider);
        crud.addDeleteListener(deleteEvent -> {
            dataProvider.delete(deleteEvent.getItem());
            deleteEvent.getItem().setEditMode(false);

        });
        crud.addSaveListener(saveEvent -> {
            String message = dataProvider.persist(saveEvent.getItem(),contactInfoModel);
            showWarning(message);
            saveEvent.getItem().setEditMode(false);
        });
        crud.addCancelListener(
                cancelEvent -> {
                    cancelEvent.getItem().setEditCount(cancelEvent.getItem().getEditCount()-1);
                    if(cancelEvent.getItem().getEditCount()== 0)
                        cancelEvent.getItem().setEditMode(false);
                });

    }
}
