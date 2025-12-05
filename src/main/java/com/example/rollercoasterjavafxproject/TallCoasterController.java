package com.example.rollercoasterjavafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
