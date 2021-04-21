module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi.ooxml;

    opens org.example to javafx.fxml;
    exports org.example;
}