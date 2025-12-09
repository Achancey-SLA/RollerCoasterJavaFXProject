package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
    public ImageView goodCoasterImageView;
    public Button imageSelectionButton;

    FileChooser fileChooser;
    public void initialize() throws Exception {
        if(GoodCoaster.getGoodCoasters().isEmpty()) {
            GoodCoaster.readGoodData();
        }
        fileChooser = new FileChooser();




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
        goodCoasterImageView.setImage(selectedCoaster.goodCoasterImage);
        nameField.setText(selectedCoaster.getName());
        parkField.setText(selectedCoaster.getPark());
        rankField.setText(String.valueOf(selectedCoaster.getRank()));
        ratingField.setText(String.valueOf(selectedCoaster.getRating()));
        locationField.setText(selectedCoaster.getCountry());
    }

    public void uploadImage(){
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            selectedCoaster.goodCoasterImage = new Image(selectedFile.toURI().toString());
            goodCoasterImageView.setImage(selectedCoaster.goodCoasterImage);
        }
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

    public void changeScene() throws IOException {
        CoasterApplication.tallScene();
    }
}
