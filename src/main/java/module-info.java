module com.example.rollercoasterjavafxproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rollercoasterjavafxproject to javafx.fxml;
    exports com.example.rollercoasterjavafxproject;
}