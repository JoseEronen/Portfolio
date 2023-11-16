package test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import test.GetLatestPrices;
import test.GetLatestPrices.Price;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.converter.LocalDateTimeStringConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ViewTable {
	
	DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
	
	
	DecimalFormat decimalFormat = new DecimalFormat("#0.000",symbols);
    private final ObservableList<Price> data = FXCollections.observableArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter formatterDayMonth = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    public ViewTable() {
    	GetLatestPrices getLatestPrices = new GetLatestPrices();
       
        
        try {
            getLatestPrices.readDataFromString(getLatestPrices.getLatestPrices());
            ObservableList<GetLatestPrices.Price> prices = getLatestPrices.getPrices();

            for (GetLatestPrices.Price price : prices) {
            	
                data.add(new Price(price.getPrice(), price.getStartDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Price> getData() {
        return data;
    }
    public TableView<Price> getTable() {
        TableView<Price> table = new TableView<>(data);

        TableColumn<Price, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Price, LocalDateTime> startDateCol = new TableColumn<>("Time");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
       
        startDateCol.setCellFactory(column -> {
            TableCell<Price, LocalDateTime> cell = new TableCell<Price, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        String formattedDate = formatter.format(item);
                        if (item.getHour() == 00) {
                            formattedDate += " ("+formatterDayMonth.format(item)+")" ;
                        }
                        setText(formattedDate);
                    }
                }
            };
            return cell;
        });
//Highlight when day changes.
        table.setRowFactory(tv -> {
            TableRow<Price> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null && newItem.getStartDate().getHour() == 0) {
                    row.setStyle("-fx-background-color: #FFFFFF");
                } else {
                    row.setStyle("");
                }
            });
            return row;
        });

        table.getColumns().setAll(priceCol, startDateCol);
        return table;
    }


    public String getCurrentPrice() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
      
        // Find the latest price that is valid for the current date and time
        Price latestPrice = null;
        for (int i = data.size()-1; i >= 0; i--) {
            Price price = data.get(i);
            if (now.isAfter(price.getStartDate()) || now.isEqual(price.getStartDate())) {
                latestPrice = price;
                             
                break;
            }
        }

        // Return the latest price, or "N/A" if there is no valid price
        if (latestPrice != null) {
            return String.valueOf(latestPrice.getPrice());
        } else {
            return "N/A";
        }
    } 

    
   public  String getNextHourPrice() throws IOException, InterruptedException {
	    
	    
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
      
        // Find the latest price that is valid for the current date and time
        Price latestPrice = null;
        for (int i = 0; i <= data.size() - 1; i++) {
            Price price = data.get(i);
            if (now.isBefore(price.getStartDate()) || now.isEqual(price.getStartDate())) {
                latestPrice = price;
                
                
                break;
            }
        }

        // Return the latest price, or "N/A" if there is no valid price
        if (latestPrice != null) {
            return String.valueOf(latestPrice.getPrice());
        } else {
            return "N/A";
        }
    }
   public String getAveragePrice() {
		Double averagePrice = 0.0;
		
		for (int i = 0;i<data.size();i++) {
			Price price = data.get(i);
			double priceValue =price.getPrice();
			averagePrice = priceValue+averagePrice;
		}
		averagePrice = averagePrice / data.size();
		String formattedPriceValue = decimalFormat.format(averagePrice);
   	return formattedPriceValue;
	}
}

