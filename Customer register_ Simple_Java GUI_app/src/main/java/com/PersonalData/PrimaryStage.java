package com.PersonalData;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryStage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Register");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Register");
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(scenetitle, 0, 0, 1, 1);

        Button addBtn = new Button("Add new information");
        HBox addHbBtn = new HBox(10);
        addHbBtn.setAlignment(Pos.BOTTOM_CENTER);
        addHbBtn.getChildren().add(addBtn);
        grid.add(addHbBtn, 0, 1);

        Button searchBtn = new Button("       Search data        ");
        HBox searchHbBtn = new HBox(10);
        searchHbBtn.setAlignment(Pos.BOTTOM_CENTER);
        searchHbBtn.getChildren().add(searchBtn);
        grid.add(searchHbBtn, 0, 2);

//For testing buttons.        
        final Text actiontarget = new Text("What you wanna do?");
        grid.add(actiontarget, 0, 4);

//Add data event
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Clicked Add new data. ");
                AddData newData = new AddData();
                newData.start(primaryStage);
            }
        });
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){  
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Clicked search data. ");
                SearchData newSearchData = new SearchData();
                newSearchData.start(primaryStage);
            }
        });
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
    
}