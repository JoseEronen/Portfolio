package com.PersonalData;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class AddData extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Add new data");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Fill the form");
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(scenetitle, 0, 0, 1, 1);

//FORM
        Text nameText = new Text("Name: ");
        grid.add(nameText,0,1);

        TextField nameField = new TextField();
        grid.add(nameField,1,1);

        Text ageText = new Text("Age: ");
        grid.add(ageText,0,2);

        TextField ageField = new TextField();
        grid.add(ageField,1,2);

        Text addressText = new Text("Address: ");
        grid.add(addressText,0,3);

        TextField addressField = new TextField();
        grid.add(addressField,1,3);

        Button submitBtn = new Button("Add");
        HBox submitHbBtn = new HBox(10);
        submitHbBtn.setAlignment(Pos.BOTTOM_CENTER);
        submitHbBtn.getChildren().add(submitBtn);
        grid.add(submitHbBtn, 1, 4);

        Text test = new Text("");
        grid.add(test,1,5);


        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            
            public void handle(ActionEvent e){
                submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String validateData = validateData(nameField.getText(), ageField.getText(), addressField.getText());
                        if (!validateData.equals("New data added")) {
                            test.setFill(Color.FIREBRICK);
                            test.setText(validateData);
                            return;
                        }
                        test.setText("New data added");
                        try {
                            final Path path = Paths.get("register.txt");
                            String fileName = "register.txt";
                            int lastId = 0;
                            try {
                                // Step 1
                                File file = new File(fileName);
                
                                // Step 2
                                FileReader fr = new FileReader(file);
                
                                // Step 3
                                BufferedReader br = new BufferedReader(fr);
                
                                // Step 4
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] customerData = line.split(",");
                                    int id = Integer.parseInt(customerData[0]);
                                    if (id > lastId) {
                                        lastId = id;
                                    }
                                }
                
                                // Step 5
                                int newId = lastId + 1;
                
                                // Step 6
                                FileWriter fw = new FileWriter(file, true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(newId + ", " +
                                        nameField.getText() + ", " +
                                        ageField.getText() + ", " +
                                        addressField.getText());
                                bw.newLine();
                                bw.close();
                                fw.close();
                
                                System.out.println("New customer added with ID " + newId);
                
                            } catch (IOException ie) {
                                System.out.println("Error reading or writing file: " + ie.getMessage());
                            }
                        } finally {
                        }
                    }});}
                });


//Back button
        Button backBtn = new Button("Back");
        HBox backHbBtn = new HBox(10);
        backHbBtn.setAlignment(Pos.BOTTOM_CENTER);
        backHbBtn.getChildren().add(backBtn);
        grid.add(backHbBtn, 1, 6);

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                PrimaryStage mainWindow = new PrimaryStage();
                mainWindow.start(primaryStage);
            }
        });

        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private String validateData(String name, String age, String address) {
        // Validate name is a string
        if (!name.matches("^[a-zA-Z0-9\\s[åäö]+$]+$")|| name =="") {
            System.out.println("Name must contain only letters");
            return "Name must contain only letters";
        }
        
        // Validate age is an integer
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 0) {
                System.out.println("Age must be a positive integer");
                return "Age must be a positive integer";
            }
        } catch (NumberFormatException e) {
            System.out.println("Age must be a positive integer");
            return "Age must be a positive integer";
        }
        
        // Validate address is a string
        if (!address.matches("^[a-zA-Z0-9\\s[åäö]+$]+$") || address =="") {
            System.out.println("Address must contain only letters, numbers, and spaces");
            return "Check the address.";
        }
        
        return "New data added";
    }
    

}
