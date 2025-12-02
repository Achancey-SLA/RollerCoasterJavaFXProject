package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TallCoasterController {
    @FXML
    private Label welcomeText;

    public void initialize() throws Exception {
        GoodCoaster.readGoodData();
        TallCoaster.readTallCoasterData();

        for (RollerCoaster eachCoaster: RollerCoaster.getCoastersList()){
            System.out.println(eachCoaster);
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
