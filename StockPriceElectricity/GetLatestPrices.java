//Create main class that creates viewTable that uses GetLatestPrices class values. First column is starttime and second value.


package test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GetLatestPrices {

    private static final String API_URL = "https://api.porssisahko.net/v1/latest-prices.json";
    public static ObservableList<Price> prices = FXCollections.observableArrayList();
    private double priceValue;
    
    public String getLatestPrices() throws IOException, InterruptedException {
    
        // Create a new HTTP client
        HttpClient client = HttpClient.newHttpClient();
        
        // Create a new HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        
        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        // Print the response body
        String latestPrices = response.body();
        
        return latestPrices;
    }
    
    public void readDataFromString(String jsonString) {
        try {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObj.getJSONArray("prices");
            
            for (int i = jsonArray.length()-1; i > 0; i--) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double priceValue = jsonObject.getDouble("price");
                
                // Parse the date string and adjust for the server's timezone
                LocalDateTime startTime = ZonedDateTime.parse(jsonObject.getString("startDate"))
                    .withZoneSameInstant(ZonedDateTime.now().getZone())
                    .toLocalDateTime();
                
                // Create a new Price object and add it to the observable list
                Price price = new Price(priceValue, startTime);
                prices.add(price);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    // Add a getter method for the prices list
    public ObservableList<Price> getPrices() {
        return prices;
    }
    public static class Price {
        private final SimpleDoubleProperty price;
        private final ObjectProperty<LocalDateTime> startDate;

        public Price(double price, LocalDateTime startDate) {
            this.price = new SimpleDoubleProperty(price);
            this.startDate = new SimpleObjectProperty<>(startDate);
        }

        public double getPrice() {
            return price.get();
        }
 
        public SimpleDoubleProperty priceProperty() {
            return price;
        }

        public LocalDateTime getStartDate() {
            return startDate.get();
        }

        public ObjectProperty<LocalDateTime> startDateProperty() {
            return startDate;
        }
    }

    
    public String getCurrentPrice() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
      
        // Find the latest price that is valid for the current date and time
        Price latestPrice = null;
        for (int i = prices.size()-1; i >= 0; i--) {
            Price price = prices.get(i);
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
        for (int i = 0; i <= prices.size() - 1; i++) {
            Price price = prices.get(i);
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
}
