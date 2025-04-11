module com.example.alif.metlife {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alif.metlife to javafx.fxml;
    exports com.example.alif.metlife;
}