package test;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Import the correct Price class
import test.GetLatestPrices;
import test.GetLatestPrices.Price;


public class HistogramApp extends Application {

    private GetLatestPrices getLatestPrices = new GetLatestPrices();
	
    
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // Get the latest prices from the API
        String latestPrices = getLatestPrices.getLatestPrices();
        
        // Read the data from the JSON string
        getLatestPrices.readDataFromString(latestPrices);
    
        // Create a new stacked bar chart with category and number axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price (cnt/kWh)");
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        barChart.setTitle("Latest Prices");
        
        // Create a new data series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //Date and time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH");
        
        // Loop through the prices and add them to the data series
        for (Price price : getLatestPrices.getPrices()) {
            // Convert the start date to a string
            String time = price.getStartDate().toString();
            LocalDateTime dateTime = LocalDateTime.parse(time);              
           
            // Add the formatted string to the x-axis label
            String label = dateTime.format(formatter);
            xAxis.getCategories().add(label);
            
            // Change the type of data to XYChart.Data<String, Number>
            series.getData().add(new XYChart.Data<String, Number>(label, price.priceProperty().getValue()));
        }  
        
        // Add the data series to the bar chart
        barChart.getData().add(series);
        
 
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
                	data.getNode().lookup(".default-color0.chart-bar").setStyle("-fx-bar-fill: green;");
                   
                }
                intVar++;
        	}
 
        
        // Create a new scene with the bar chart and show it on the stage
        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

	public Parent getRootNode() {
		// TODO Auto-generated method stub
		return null;
	}
}