package com.projet.projet.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ajoutOuvController implements Initializable {
    @FXML
    private ChoiceBox<String> choix_type;

    @FXML
    private TextField tfisbn;

    @FXML
    private TextField tfnbexemp;

    @FXML
    private TextField tfprix;

    @FXML
    private TextField tfseller;

    @FXML
    private TextField tftitre;


    private String[] options= {"Livre","Magazine", "Bande déssinée", "Dictionnaire"};








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choix_type.getItems().addAll(options);








    }
}
