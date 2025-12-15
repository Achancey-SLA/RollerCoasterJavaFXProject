module com.example.rollercoasterjavafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.swing;

    opens com.example.rollercoasterjavafxproject to javafx.fxml;
    exports com.example.rollercoasterjavafxproject;
}