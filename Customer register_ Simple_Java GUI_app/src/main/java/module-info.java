module com.helloworld {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.PersonalData to javafx.fxml;
    exports com.PersonalData;
}
