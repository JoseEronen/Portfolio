//TODO: search bar!



package com.PersonalData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchData extends Application {

    private final String FILE_NAME = "register.txt";
    private TableView<Customer> table = new TableView<>();
    private List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        TableColumn<Customer, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        TableColumn<Customer, Integer> ageCol = new TableColumn<>("Age");
        TableColumn<Customer, String> addressCol = new TableColumn<>("Address");


        // set cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("age"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));

        // enable editing of customer cells
        table.setEditable(true);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // handle updates to customer data
        nameCol.setOnEditCommit(e -> {
            Customer customer = e.getRowValue();
            customer.setName(e.getNewValue());
            saveDataToFile();
        });
        ageCol.setOnEditCommit(e -> {
            Customer customer = e.getRowValue();
            customer.setAge(e.getNewValue());
            saveDataToFile();
        });
        addressCol.setOnEditCommit(e -> {
            Customer customer = e.getRowValue();
            customer.setAddress(e.getNewValue());
            saveDataToFile();
        });

        primaryStage.setTitle("Customer Info");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);

        // add columns to table
        table.getColumns().addAll(idCol, nameCol, ageCol, addressCol);
        table.setPrefWidth(450);
        table.setPrefHeight(400);

        // read data from file and add to list
        readDataFromFile();
        showAllCustomers();
        // add table to grid
        grid.add(table, 0, 0);

        // create "Back" button
        Button backBtn = new Button("Back");
        
        // create "Refresh" button
        Button refreshBtn = new Button("Refresh");
        // create "Delete" button
        Button deleteBtn = new Button("Delete");

        // create HBox to contain buttons
        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.CENTER);
        backBtn.setAlignment(Pos.BOTTOM_LEFT);
        buttonsHBox.getChildren().addAll(backBtn,refreshBtn, deleteBtn);

        // add HBox containing buttons to grid
        grid.add(buttonsHBox, 0, 1);
        
        // set button actions
        refreshBtn.setOnAction(e -> showAllCustomers());
        backBtn.setOnAction(e -> {
            PrimaryStage mainWindow = new PrimaryStage();
            mainWindow.start(primaryStage);
        });
  
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customer customer = table.getSelectionModel().getSelectedItem();
                if (customer != null) {
                    customers.remove(customer);
                    for (int i = 0; i < customers.size(); i++) {
                        customer = customers.get(i);
                        customer.setId(i + 1);
                    }
                    saveDataToFile();
                    showAllCustomers();
                }
            }
        });

        primaryStage.show();
    }

    private void readDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                String address = data[3].trim();
                Customer customer = new Customer(id, name, age, address);
                customers.add(customer);

    //For testing..
                System.out.println("Added customer: " + customer.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void showAllCustomers() {
        // clear the table view
        table.getItems().clear();
        // read the data from the file and add to the customers list
        customers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                String address = data[3].trim();
                Customer customer = new Customer(id, name, age, address);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // add the customers to the table view
        table.getItems().addAll(customers);
    }
    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (Customer customer : customers) {
                writer.println(customer.getId() + "," + customer.getName() + "," + customer.getAge() + "," + customer.getAddress());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class Customer {
        

        private int id;
        private String name;
        private int age;
        private String address;

        public Customer(int id, String name, int age, String address) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }
        public void setId(int id) {
            this.id = id;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
        
        public void update(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
    
}

