package com.it.ittest.jfx.controller;

import com.it.ittest.jfx.model.Employee;
import com.it.ittest.jfx.rest.NewJerseyClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class EmployeeEditDialog {

    private TableView<Employee> tableEmployees;

    private NewJerseyClient jerseyClient;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField secondNameTextField;
    @FXML
    private TextField lastNameTextField;

    private Employee employee;

    @FXML
    private void updateEmployee(ActionEvent event) {

        String lastName = lastNameTextField.getText().trim();
        String firstName = firstNameTextField.getText().trim();
        String secondName = secondNameTextField.getText().trim();

        StringBuilder employeeName = new StringBuilder();
        StringBuilder employeeAlias = new StringBuilder();

        if (!lastName.isEmpty()) {
            employee.setLastName(lastName);
        }

        employeeName.append(employee.getLastName());
        employeeAlias.append(employee.getLastName());

        if (!firstName.isEmpty()) {
            employee.setFirstName(firstName);
            employeeName.append(" ").append(employee.getFirstName());
            employeeAlias.append(" ").append(employee.getFirstName().charAt(0));
        } else {
            employeeName.append(employee.getFirstName());
            employeeAlias.append(employee.getFirstName());
        }
        if (!secondName.isEmpty()) {
            employee.setSecondName(secondName);
            employeeName.append(" ").append(employee.getSecondName());
            employeeAlias.append(" ").append(employee.getSecondName().charAt(0));
        } else {
            employeeName.append(employee.getSecondName());
            employeeAlias.append(employee.getSecondName());
        }

        employee.setName(employeeName.toString());
        employee.setAlias(employeeAlias.toString());

        new Thread(() -> jerseyClient.edit_JSON(employee)).start();

        tableEmployees.refresh();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    void setEmployee(Employee employee) {
        this.employee = employee;

        firstNameTextField.setText(employee.getFirstName());
        secondNameTextField.setText(employee.getSecondName());
        lastNameTextField.setText(employee.getLastName());
    }

    @FXML
    private void closeEditDialog(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    void setTableView(TableView<Employee> tableEmployees) {
        this.tableEmployees = tableEmployees;
    }

    void setJerseyClient(NewJerseyClient jerseyClient) {
        this.jerseyClient = jerseyClient;
    }
}
