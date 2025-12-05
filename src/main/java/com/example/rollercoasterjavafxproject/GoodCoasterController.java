package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GoodCoasterController {
    @FXML
    private Label welcomeText;
    public ListView<GoodCoaster> goodCoasterListView;
    public TextField nameField;
    public TextField rankField;
    public TextField locationField;
    public TextField parkField;
    public TextField ratingField;
    public GoodCoaster selectedCoaster;

    public void initialize() throws Exception {
        GoodCoaster.readGoodData();
        TallCoaster.readTallCoasterData();

        for (GoodCoaster eachCoaster: GoodCoaster.getGoodCoasters()){
            goodCoasterListView.getItems().add(eachCoaster);
        }

        goodCoasterListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // oldValue can be null if nothing WAS selected
                    // newValue can be null if nothing IS NOW selected
                    if (newValue != null) {
                        selectedCoaster = newValue;
                        selectCoaster();
                    } else {
                        unselectCoaster();
                    }
                });
    }


    public void selectCoaster(){
        nameField.setText(selectedCoaster.getName());
        parkField.setText(selectedCoaster.getPark());
        rankField.setText(String.valueOf(selectedCoaster.getRank()));
        ratingField.setText(String.valueOf(selectedCoaster.getRating()));
        locationField.setText(selectedCoaster.getCountry());
    }
    public void unselectCoaster(){
        nameField.setText("");
        parkField.setText("");
        rankField.setText("");
        ratingField.setText("");
        nameField.setText("");
    }

    public void editCoasterData(){
        System.out.println("edit");
        selectedCoaster.name = nameField.getText();
        selectedCoaster.rank = Integer.parseInt(rankField.getText());
        selectedCoaster.setPark(parkField.getText());
        selectedCoaster.setCountry(locationField.getText());
        selectedCoaster.setRating(Float.parseFloat(ratingField.getText()));
        goodCoasterListView.refresh();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
