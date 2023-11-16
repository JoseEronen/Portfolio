package test;


	import javafx.scene.control.Button;
	import javafx.scene.layout.HBox;
	import javafx.scene.input.KeyCode;
	import javafx.scene.input.KeyEvent;
	import javafx.collections.ListChangeListener;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	
	import javafx.scene.layout.StackPane;
	import javafx.application.Application;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.scene.control.TextFormatter;
	import javafx.scene.control.TextFormatter.Change;
	import javafx.scene.Node;
	import javafx.scene.layout.VBox;
	
	import javafx.scene.paint.Color;
	import javafx.scene.shape.Line;
	import javafx.scene.Scene;
	import javafx.scene.chart.CategoryAxis;
	import javafx.scene.chart.NumberAxis;
	import javafx.scene.chart.StackedBarChart;
	import javafx.scene.chart.LineChart;
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
	import test.GetLatestPrices;
	import test.GetLatestPrices.Price;
	import test.ViewTable;
	import java.io.Serializable;
	import java.util.Iterator;
	import java.util.List;
	

	public class TableViewToLineChart extends Application {

	    private TableView<Price> tableView = new TableView<Price>();
	    private XYChart.Series<String, Number> series;

	    @Override
	    public void start(final Stage primaryStage) throws Exception {
	    	java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM HH");

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

	        // Create current price label
	        Label priceNowLabel = new Label("Current Price: " + tableView.getAveragePrice() + "         ");

	        // Create nexthour  price label
	        Label priceNextHourLabel = new Label("Next Hour Price: " + tableView.getNextHourPrice());

	        // create line chart
	        CategoryAxis xAxis = new CategoryAxis();

	        NumberAxis yAxis = new NumberAxis();

	        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

	        // Add table data to the lineChart
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	        series.setName("Price");

	        for (Price price : tableView.getData()) {
	            // Convert the start date to a string
	            String time = price.getStartDate().toString();
	            java.time.LocalDateTime dateTime = java.time.LocalDateTime.parse(time);

	            // Add the formatted string to the x-axis label
	            String label = dateTime.format(formatter);
	            xAxis.getCategories().add(label);

	            // Change the type of data to XYChart.Data<String, Number>
	            series.getData().add(new XYChart.Data<>(label, price.priceProperty().getValue()));
	        }


	        // Add the data series to the line chart
	        lineChart.getData().add(series);

	        // Get the current time
	        LocalDateTime currentTime = LocalDateTime.now();
	        String formattedCurrentTime = currentTime.format(formatter);

	        ObservableList<String> labels = xAxis.getCategories();

	        // Loop through the data series and highlight the bar with the current date and hour
	        int intVar = 0;

	        for (String label : labels) {
	            // Get the time from the LocalDateTime object as a string
	            String time = label.formatted(DateTimeFormatter.ofPattern("dd-MM HH"));

	            if (time.equals(formattedCurrentTime)) {
	                XYChart.Data<String, Number> data = series.getData().get(intVar);
	                data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: yellow;");
	            }
	            intVar++;
	        }
	        
	        // Create the current time line
	        Line verticalLine = new Line();
	        verticalLine.setStroke(Color.BLUE);
	        verticalLine.setStrokeWidth(200);

	        // Update the current time line position periodically
	        xAxis.getCategories().addListener((javafx.collections.ListChangeListener.Change<? extends String> change) -> {
	            double chartWidth = lineChart.getWidth();
	            double chartHeight = lineChart.getHeight();
	            double xPosition = chartWidth * (currentTime.toLocalTime().toSecondOfDay()); // Scale the position based on the total seconds in a day

	            verticalLine.setStartX(xPosition);
	            verticalLine.setEndX(xPosition);
	            verticalLine.setStartY(0);
	            verticalLine.setEndY(chartHeight);
	        });
	       // StackPane chartPlotArea = (StackPane) lineChart.lookup(".chart-plot-background");
	       // chartPlotArea.getChildren().add(verticalLine);
	        
	        

	        // Reads the text fields when enter is pressed

	        lowPriceTextField.setOnKeyPressed(event -> {
	            handleTextFieldKeyPress(event, lowPriceTextField, highPriceTextField, series);
	        });

	        highPriceTextField.setOnKeyPressed(event -> {
	            handleTextFieldKeyPress(event, lowPriceTextField, highPriceTextField, series);
	        });

	        GridPane gridPane = new GridPane();
	        gridPane.setStyle("-fx-padding: 15px;" + "-fx-background-color: #f2f2f2");

	        // Create a column constraint for column 1 with a fixed width of 175px
	        ColumnConstraints column1Constraints = new ColumnConstraints();
	        column1Constraints.setPrefWidth(175);
	        gridPane.getColumnConstraints().add(column1Constraints);

	        // Create a column constraint for column 2 that makes the column fill all available space
	        ColumnConstraints column2Constraints = new ColumnConstraints();
	        column2Constraints.setFillWidth(true);
	        column2Constraints.setHgrow(Priority.ALWAYS);
	        gridPane.getColumnConstraints().add(column2Constraints);

	        // Create a row constraint for row 1 that sets the preferred height to a small value
	        RowConstraints row1Constraints = new RowConstraints();
	        row1Constraints.setPrefHeight(30);
	        gridPane.getRowConstraints().add(row1Constraints);

	        // Create a row constraint for row 2 that sets the preferred height to a small value
	        RowConstraints row2Constraints = new RowConstraints();
	        row2Constraints.setPrefHeight(20);
	        gridPane.getRowConstraints().add(row2Constraints);

	        RowConstraints row3Constraints = new RowConstraints();
	        row3Constraints.setPrefHeight(30);
	        gridPane.getRowConstraints().add(row3Constraints);

	        // Create a row constraint for row 3 that makes the row fill all available space
	        RowConstraints row4Constraints = new RowConstraints();
	        row4Constraints.setFillHeight(true);
	        row4Constraints.setVgrow(Priority.ALWAYS);

	        gridPane.getRowConstraints().add(row4Constraints);
	        gridPane.add(lowPriceTextField, 0, 0);
	        gridPane.add(highPriceTextField, 0, 1);
	        gridPane.add(priceNowLabel, 0, 2, 1, 1);
	        gridPane.add(priceNextHourLabel, 1, 2, 1, 1);
	        gridPane.add(table, 0, 3, 1, 1); // use column span = 1, row span = 2 to take all available space
	        gridPane.add(lineChart, 1, 3);

	        // Add a listener to the table to detect selection changes
	        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	            if (newSelection != null) {
	                // Get the X-axis value of the selected cell and format it
	                LocalDateTime selectedDateTime = newSelection.getStartDate();
	                String selectedXAxisValue = selectedDateTime.format(DateTimeFormatter.ofPattern("dd.MM HH"));

	                // Highlight the corresponding data point in the chart
	                for (XYChart.Data<String, Number> data : series.getData()) {
	                    if (data.getXValue().equals(selectedXAxisValue)) {
	                        data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: green;");
	                    } else {
	                        data.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: default-color0;");
	                    }
	                }
	            }
	        });

	        // Add a listener to the lineChart to detect selection changes
	        lineChart.setOnMouseClicked(event -> {
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
	                // Highlight the selected data point in the chart
	                selectedData.getNode().lookup(".chart-line-symbol").setStyle("-fx-background-color: green;");

	                // Select the corresponding table cell
	                int selectedIndex = xAxis.getCategories().indexOf(selectedData.getXValue());
	                table.getSelectionModel().select(selectedIndex);
	                table.scrollTo(selectedIndex);
	            }
	        });

	        // Create scene and show stage
	        Scene scene = new Scene(gridPane, 800, 600);
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
	                        node.setStyle("-fx-background-color: green;");
	                    } else if (highPriceLimit != null && value >= highPriceLimit) {
	                        node.setStyle("-fx-background-color: red;");
	                    } else {
	                        node.setStyle("-fx-background-color: default-color0;");
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


