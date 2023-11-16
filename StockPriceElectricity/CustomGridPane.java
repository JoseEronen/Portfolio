package test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class CustomGridPane {
	
    static GridPane createGridPane(String title) {
        GridPane gridPane = new GridPane();
        Label titleLabel = new Label(title);
        gridPane.setStyle("-fx-padding: 10px;"+"-fx-background-color: #f2f2f2");
        // Add nodes to the gridPane as desired
        gridPane.setGridLinesVisible(true);
        gridPane.add(titleLabel, 0, 0);

        return gridPane;
    }
    
}
