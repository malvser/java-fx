package com.it.ittest.jfx.controller;

import com.it.ittest.jfx.model.Employee;
import com.it.ittest.jfx.rest.NewJerseyClient;
import com.sun.jersey.api.client.GenericType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class EmployeeController {

    private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

    private NewJerseyClient jerseyClient;

    @FXML
    private TableView<Employee> tableEmployees;

    @FXML
    private TableColumn<Employee, Long> idColumn;
    @FXML
    private TableColumn<Employee, Long> originalidColumn;
    @FXML
    private TableColumn<Employee, String> aliasColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> secondNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, Date> createDateColumn;
    @FXML
    private TableColumn<Employee, String> refinedColumn;
    @FXML
    private TableColumn<Employee, Long> sortNoColumn;
    @FXML
    private TableColumn<Employee, Short> hiddenColumn;
    @FXML
    private TableColumn<Employee, String> passwordColumn;
    @FXML
    private TableColumn<Employee, String> saltColumn;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        originalidColumn.setCellValueFactory(new PropertyValueFactory<>("originalid"));
        aliasColumn.setCellValueFactory(new PropertyValueFactory<>("alias"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        refinedColumn.setCellValueFactory(new PropertyValueFactory<>("refined"));
        sortNoColumn.setCellValueFactory(new PropertyValueFactory<>("sortNo"));
        hiddenColumn.setCellValueFactory(new PropertyValueFactory<>("hidden"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        saltColumn.setCellValueFactory(new PropertyValueFactory<>("salt"));

        // заполняем таблицу данными
        tableEmployees.setItems(employeeData);

        tableEmployees.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    showEditEmployeeDialog(row.getItem());
                }
            });
            return row ;
        });
    }

    private void showEditEmployeeDialog(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditEmployee.fxml"));
            AnchorPane anchorPane = loader.load();

            EmployeeEditDialog emb = loader.getController();
            emb.setEmployee(employee);
            emb.setTableView(tableEmployees);
            emb.setJerseyClient(jerseyClient);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(anchorPane));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        new Thread(() -> {
            jerseyClient = new NewJerseyClient("employees");
            GenericType<List<Employee>> type = new GenericType<List<Employee>>() {};
            List<Employee> employees = jerseyClient.findAll_JSON(type);
            employeeData.addAll(employees);
        }).start();
    }
}
