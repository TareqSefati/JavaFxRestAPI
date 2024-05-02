module com.example.javafxrestapi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxrestapi to javafx.fxml;
    exports com.example.javafxrestapi;
}