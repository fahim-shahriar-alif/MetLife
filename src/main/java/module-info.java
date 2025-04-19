module com.example.alif.metlife {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.alif.metlife to javafx.fxml;
    exports com.example.alif.metlife;
    exports com.example.alif.metlife.Alif_2221079;
    opens com.example.alif.metlife.Alif_2221079 to javafx.fxml;

    exports com.example.alif.metlife.InzamamulHoque_1910014;
    opens com.example.alif.metlife.InzamamulHoque_1910014 to javafx.fxml;
}