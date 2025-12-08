package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class TallCoasterController {
    @FXML
    private Label welcomeText;
    public TableView <TallCoaster> TallCoasterData;
    public TableColumn <TallCoaster, Integer> rankColumn;
    public TableColumn <TallCoaster, String> titleColumn;
    public TableColumn <TallCoaster, String> LocationColumn;
    public TableColumn <TallCoaster, Boolean> ActiveColumn;
    public TableColumn <TallCoaster, LocalDate> DateColumn;
    public TableColumn <TallCoaster, Float> HeightColumn;

    public ImageView TallCoasterImageView;
    TallCoaster selectedCoaster;


    public void initialize() throws Exception {
        GoodCoaster.readGoodData();
        TallCoaster.readTallCoasterData();
        for (TallCoaster eachCoaster: TallCoaster.getTallcoastersList()){
            TallCoasterData.getItems().add(eachCoaster);

        }
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("park"));
        ActiveColumn.setCellValueFactory(new PropertyValueFactory<>("operating"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("opened"));
        HeightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        TallCoasterData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    if (newValue != null) {
                        selectedCoaster = newValue;
                        TallCoasterImageView.setImage(selectedCoaster.TallCoasterImages);
                    } else {

                    }
                });
    }

    FileChooser fileChooser = new FileChooser();

    public void selectImage() {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("Images that might work");
            System.out.println(selectedFile.getPath());
            selectedCoaster.TallCoasterImages = new Image(selectedFile.toURI().toString());
            TallCoasterImageView.setImage(selectedCoaster.TallCoasterImages);
        }

    }
    public void selectColumn(){

    }

    public void changeScene() throws IOException {
CoasterApplication.goodScene();
    }



}
