package test;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import test.GetLatestPrices;
import test.GetLatestPrices.Price;
import test.ViewTable;
import test.CustomGridPane;

public class TableViewToBarChart extends Application {
	
	private TableView<Price> tableView = new TableView<Price>();
	private XYChart.Series<String, Number> series;
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH");

        primaryStage.setTitle("Hybrid view");
        
        TextField lowPriceTextField = new TextField();  
        lowPriceTextField.setPromptText("Set low limit");
        setupNumericTextField(lowPriceTextField);
        
        TextField highPriceTextField = new TextField("");
        highPriceTextField.setPromptText("Set high limit");
        setupNumericTextField(highPriceTextField);
        
        // create table view and add columns
	    ViewTable tableView = new ViewTable(); 
	    TableView<GetLatestPrices.Price> table = tableView.getTable();
	    
	    
	    
	   
        //Create current price label
	    Label priceNowLabel = new Label("Current Price: " + tableView.getCurrentPrice()+"         ");
	 
        //Create nexthour  price label
	    Label priceNextHourLabel = new Label("Next Hour Price: " + tableView.getNextHourPrice());
	    
        //Create nexthour  price label
	    Label AveragePrice = new Label("Average Price: " + tableView.getAveragePrice());
        // create bar chart  
        CategoryAxis xAxis = new CategoryAxis();
        
        NumberAxis yAxis = new NumberAxis();
                  
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setBarGap(0);  // Set a negative value to reduce the width of the bars
        barChart.setCategoryGap(0);  // Set the category gap to 0

        // Add table data to the barChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Price");
        
        for (Price price : tableView.getData()) {
            // Convert the start date to a string
            String time = price.getStartDate().toString();
            LocalDateTime dateTime = LocalDateTime.parse(time);
          
            // Add the formatted string to the x-axis label
            String label = dateTime.format(formatter);
            xAxis.getCategories().add(label);

            // Change the type of data to XYChart.Data<String, Number>
            series.getData().add(new XYChart.Data<>(label, price.priceProperty().getValue()));
        }

        // Add the data series to the bar chart
        barChart.getData().add(series);
        

        
        // Set negative bar color Green style 
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {
                double value = data.getYValue().doubleValue();
                if (value < 0) {
                    node.setStyle("-fx-bar-fill: green;");
                } else {
                    node.setStyle(""); // Reset the style for non-negative values
                }
            }
        }
        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedCurrentTime = currentTime.format(formatter);
        
        
                ObservableList<String> labels = xAxis.getCategories();
        
        // Loop through the data series and highlight the bar with the current date and hour
        int intVar =0;
             	
        	for (String label : labels) {

        	    // Get the time from the LocalDateTime object as a string
        	    String time = label.formatted(DateTimeFormatter.ofPattern("dd-MM HH"));
        	    
                if (time.equals(formattedCurrentTime)) {
                	XYChart.Data<String, Number> data = series.getData().get(intVar);
                	data.getNode().lookup(".default-color0.chart-bar").setStyle("-fx-bar-fill: yellow;");
                   }
                intVar++;
        	}
        	
        //Reads the textfields when enter is pressed
        	
    	lowPriceTextField.setOnKeyPressed(event -> {
    	    handleTextFieldKeyPress(event, lowPriceTextField, highPriceTextField,series);
    		});

    	highPriceTextField.setOnKeyPressed(event -> {
    	    handleTextFieldKeyPress(event, lowPriceTextField, highPriceTextField,series);
		});  
	     
	  // Add a listener to the table to detect selection changes
	   
	     table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	    	    if (newSelection != null) {
	    	        // Get the X-axis value of the selected cell and format it
	    	        LocalDateTime selectedDateTime = newSelection.getStartDate();
	    	        String selectedXAxisValue = selectedDateTime.format(DateTimeFormatter.ofPattern("dd.MM HH"));
	    	     
	    	        // Highlight the corresponding bar in the chart
	    	        for (BarChart.Data<String, Number> data : series.getData()) {
	    	        	
	    	            String firstIndexValue = data.getXValue().substring(0, 8);
	    	            
	    	            // Get the first 6 characters from the X-axis value
	    	            if (firstIndexValue.equals(selectedXAxisValue)) {
	    	            	
	    	                data.getNode().lookup(".default-color0.chart-bar").setStyle("-fx-bar-fill: green;");
	    	            } else {
	    	                data.getNode().lookup(".default-color0.chart-bar").setStyle(".default-color0.chart-bar");
	    	            }
	    	        }
	    	    }
	    	});
	
	  // Add a listener to the barChart to detect selection changes
	     barChart.setOnMouseClicked(event -> {
	         // Convert mouse click coordinates to xAxis local coordinates
	         double xAxisX = xAxis.sceneToLocal(event.getSceneX(), 0).getX();
	
	         // Get the selected data point
	         String selectedLabel = xAxis.getValueForDisplay(xAxisX);
	         XYChart.Data<String, Number> selectedData = null;
	
	         // Find the corresponding data point in the series
	         for (XYChart.Data<String, Number> data : series.getData()) {
	             if (data.getXValue().equals(selectedLabel)) {
	                 selectedData = data;
	                 break;
	             }
	         }

         if (selectedData != null) {
             // Highlight the selected bar in the chart
             selectedData.getNode().lookup(".default-color0.chart-bar").setStyle("-fx-bar-fill: green;");

             // Select the corresponding table cell
             int selectedIndex = xAxis.getCategories().indexOf(selectedData.getXValue());
             table.getSelectionModel().select(selectedIndex);
             table.scrollTo(selectedIndex);
         }
     });
	     
	    //Tästä alkaa gridien kanssa sekoilu....
	     
	   GridPane gridPane1 = CustomGridPane.createGridPane("grid 1");
		 RowConstraints row1Constraints = new RowConstraints();
		 row1Constraints.setPrefHeight(30);
		 gridPane1.getRowConstraints().add(row1Constraints);
		 
		 // Create a row constraint for row 2 that sets the preferred height to a small value
		 RowConstraints row2Constraints = new RowConstraints();
		 row2Constraints.setPrefHeight(30);
		 gridPane1.getRowConstraints().add(row2Constraints);
		 
		 RowConstraints row3Constraints = new RowConstraints();
		 row3Constraints.setPrefHeight(30);
		 gridPane1.getRowConstraints().add(row3Constraints);
		 
		 ColumnConstraints column1Constraints = new ColumnConstraints();
		 column1Constraints.setPrefWidth(175);
		 
		 
		 gridPane1.getColumnConstraints().add(column1Constraints);
		 ColumnConstraints column2Constraints = new ColumnConstraints();
		 column2Constraints.setFillWidth(true);
		 gridPane1.getColumnConstraints().add(column2Constraints);
	
		 ColumnConstraints column3Constraints = new ColumnConstraints();
		 column3Constraints.setFillWidth(true);
		 column3Constraints.setMaxWidth(Double.MAX_VALUE);
		 gridPane1.getColumnConstraints().add(column3Constraints);
		 
		 
		 Label empty = new Label("                                  ");
		 gridPane1.add(lowPriceTextField,0, 0);
		 gridPane1.add(highPriceTextField,0,1);
		 gridPane1.add(empty, 1,1);
		 gridPane1.add(priceNowLabel, 2, 0);
		 gridPane1.add(priceNextHourLabel, 2, 1);
		 gridPane1.add(AveragePrice, 2,2);
		 
	    GridPane gridPane2 = CustomGridPane.createGridPane("grid 2");
	   
	    
		 // Create a row constraint for row 3 that makes the row fill all available space
		 RowConstraints row4Constraints = new RowConstraints();
		 row4Constraints.setVgrow(Priority.ALWAYS);
		 row4Constraints.setFillHeight(true);
		 gridPane2.getRowConstraints().add(row4Constraints);
		 
		  // Create a column constraint for column 1 with a fixed width of 175px
		 ColumnConstraints column4Constraints = new ColumnConstraints();
		 column4Constraints.setPrefWidth(175);
		 //column4Constraints.setHgrow(Priority.ALWAYS);
		 column4Constraints.setFillWidth(true);
		 gridPane2.getColumnConstraints().add(column4Constraints);
		 
		 // Create a column constraint for column 2 that makes the column fill all available space
		 ColumnConstraints column5Constraints = new ColumnConstraints();
		 column5Constraints.setFillWidth(true);
		 column5Constraints.setHgrow(Priority.ALWAYS);
		 gridPane2.getColumnConstraints().add(column5Constraints);

		 gridPane2.add(table, 0, 0); // use column span = 1, row span = 2 to take all available space
		 gridPane2.add(barChart, 1, 0);

		 VBox vbox = new VBox(gridPane1, gridPane2);
		 VBox.setVgrow(vbox, Priority.ALWAYS); //Takes all available space.
		 vbox.setStyle("-fx-border-color: red; -fx-border-width: 2px;"); //Debugging purposes un
		 
		// create scene and show stage
		Scene scene = new Scene(vbox, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
    private void handleTextFieldKeyPress(KeyEvent event, TextField lowPriceTextField, TextField highPriceTextField, XYChart.Series<String, Number> series) {
        if (event.getCode() == KeyCode.ENTER) {
            String lowPriceText = lowPriceTextField.getText();
            String highPriceText = highPriceTextField.getText();

            Double lowPriceLimit = null;
            Double highPriceLimit = null;

            // Parse the lowPriceText if it is not empty
            if (!lowPriceText.isEmpty()) {
                lowPriceLimit = Double.parseDouble(lowPriceText);
            }

            // Parse the highPriceText if it is not empty
            if (!highPriceText.isEmpty()) {
                highPriceLimit = Double.parseDouble(highPriceText);
            }

            for (XYChart.Data<String, Number> data : series.getData()) {
                Node node = data.getNode();
                if (node != null) {
                    double value = data.getYValue().doubleValue();
                    if (lowPriceLimit != null && value <= lowPriceLimit) {
                        node.setStyle("-fx-bar-fill: green;");
                    } else if (highPriceLimit != null && value >= highPriceLimit) {
                        node.setStyle("-fx-bar-fill: red;");
                    } else {
                        node.setStyle(""); // Reset the style
                    }
                }
            }
        }
    }
    private void setupNumericTextField(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) {
                return change;
            }
            return null;
        }));
    }
}