package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import javax.xml.stream.Location;
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
        if(TallCoaster.getCoastersList().isEmpty()) {
            TallCoaster.readTallCoasterData();
        }
        for (TallCoaster eachCoaster: TallCoaster.getTallcoastersList()){
            TallCoasterData.getItems().add(eachCoaster);

        }
        rankColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("park"));
        LocationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ActiveColumn.setCellValueFactory(new PropertyValueFactory<>("operating"));
        ActiveColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("opened"));
        DateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        HeightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        HeightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        TallCoasterData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->{
                    if (newValue != null) {
                        selectedCoaster = newValue;
                        TallCoasterImageView.setImage(selectedCoaster.TallCoasterImages);
                    } else {

                    }
                });
        rankColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, Integer> t) -> {
                    selectedCoaster.setRank(t.getNewValue());
                });
        titleColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, String> t) -> {
                    selectedCoaster.setName(t.getNewValue());
                });
        LocationColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, String> t) -> {
                    selectedCoaster.setPark(t.getNewValue());
                });
        ActiveColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, Boolean> t) -> {
                    selectedCoaster.setOperating(t.getNewValue());
                });
        DateColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, LocalDate> t) -> {
                    selectedCoaster.setOpened(t.getNewValue());
                });
        HeightColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TallCoaster, Float> t) -> {
                    selectedCoaster.setHeight(t.getNewValue());
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

    public void editData(){
        selectedCoaster.name = titleColumn.getCellData(TallCoaster.tallCoastersList.indexOf(selectedCoaster));
        System.out.println(selectedCoaster);
    }



}
