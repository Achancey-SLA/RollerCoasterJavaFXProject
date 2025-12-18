package com.example.rollercoasterjavafxproject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

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
    public Image noImage;
    boolean multipleSelected;
    ArrayList<GoodCoaster> inputList;
    ObservableList<GoodCoaster> selectedItems;
    FileChooser fileChooser;
    public void initialize() throws Exception {

        noImage = new Image(new FileInputStream("noImage.png"));

        goodCoasterListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            GoodCoaster.restoreData();
        } catch (Exception ex) {

        }

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
                        selectedItems = goodCoasterListView.getSelectionModel().getSelectedItems();
                        if(selectedItems.size()==1) {
                            selectSingleCoaster();
                        }
                        else{
                            selectMultipleCoasters();
                        }
                    }
                    else {
                        unselectCoaster();
                    }
                });
    }

    public void selectMultipleCoasters(){
        multipleSelected = true;
        nameField.setText("");
        parkField.setText("");
        rankField.setText("");
        ratingField.setText("");
        nameField.setText("");
        locationField.setText("");
        goodCoasterImageView.setImage(noImage);

    }

    public void selectSingleCoaster(){
        multipleSelected = false;
        if(selectedCoaster.goodCoasterImage!=null) {
            goodCoasterImageView.setImage(selectedCoaster.goodCoasterImage);
        } else{
            goodCoasterImageView.setImage(noImage);
        }
        nameField.setText(selectedCoaster.getName());
        parkField.setText(selectedCoaster.getPark());
        rankField.setText(String.valueOf(selectedCoaster.getRank()));
        ratingField.setText(String.valueOf(selectedCoaster.getRating()));
        locationField.setText(selectedCoaster.getCountry());
    }

    public void uploadImage(){
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            for(GoodCoaster eachSelected:selectedItems){
                eachSelected.goodCoasterImage = new Image(selectedFile.toURI().toString());
                goodCoasterImageView.setImage(selectedCoaster.goodCoasterImage);

            }

        }
    }
    public void unselectCoaster(){
        nameField.setText("");
        parkField.setText("");
        rankField.setText("");
        ratingField.setText("");
        nameField.setText("");
        locationField.setText("");
    }

    public void editCoasterData() throws Exception {
        System.out.println("edit");
        if(!multipleSelected) {
            selectedCoaster.name = nameField.getText();
            selectedCoaster.rank = Integer.parseInt(rankField.getText());
            selectedCoaster.setPark(parkField.getText());
            selectedCoaster.setCountry(locationField.getText());
            selectedCoaster.setRating(Float.parseFloat(ratingField.getText()));
            goodCoasterListView.refresh();
            GoodCoaster.saveData();
        }
        else{
            for (GoodCoaster eachSelected: selectedItems){
                if(!nameField.getText().isEmpty()) {
                    eachSelected.name = nameField.getText();
                }
                if(!rankField.getText().isEmpty()) {
                    eachSelected.setRank(Integer.parseInt(rankField.getText()));
                }
                if(!parkField.getText().isEmpty()) {
                    eachSelected.setPark(parkField.getText());
                }
                if(!locationField.getText().isEmpty()) {
                    eachSelected.setCountry(locationField.getText());
                }
                if(!ratingField.getText().isEmpty()) {
                    eachSelected.setRating(Float.parseFloat(ratingField.getText()));
                }
                goodCoasterListView.refresh();
                GoodCoaster.saveData();
            }
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void changeScene() throws IOException {
        CoasterApplication.tallScene();
    }


}
