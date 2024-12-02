package com.projet.projet.controllers;

import com.projet.projet.dataManagement.AdherentDAO;
import com.projet.projet.dataManagement.EmpruntDAO;
import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.pret.prets;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RestituerEmpruntController implements Initializable {

    @FXML
    private Button b_supprimer;

    @FXML
    private DatePicker dateemp;

    @FXML
    private DatePicker dateretour;


    @FXML
    private TextField tfcin;

    @FXML
    private TextField tfisbn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_supprimer.setOnMouseClicked(e->restituerEmprunt());


    }


    private void restituerEmprunt(){
        String cin = tfcin.getText();
        String isbn = tfisbn.getText();
        LocalDate emp = dateemp.getValue();
        LocalDate retour = dateretour.getValue();
        if(cin.isBlank() || !(SceneMethods.isInteger(cin)) || isbn.isBlank() || emp==null || retour==null || !(SceneMethods.isInteger(isbn))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vérifier les champs de texte!");
            alert.setContentText("Prière de remplir tous les champs disponibles.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert.showAndWait();

        }

        else {
            if( retour.isBefore(emp)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vérifier les champs de Date!");
                alert.setContentText("Date de retour entré est avant la date d'emprunt!");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                alert.showAndWait();
                dateemp.setValue(null);
                dateretour.setValue(null);
            }else {
                prets pret = new prets(Integer.parseInt(cin), isbn, emp, retour);
                int res = EmpruntDAO.updateEmprunt(pret);
                switch (res) {
                    case -1:
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur ");
                        alert.setHeaderText("Echec d'ajout de l'emprunt!");
                        alert.setContentText("Un y'a une erreur lors du retour de l'emprunt, essayez de nouveau!");
                        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        dateretour.setValue(null);
                        dateemp.setValue(null);
                        tfisbn.clear();
                        tfcin.clear();
                        break;
                    case 0:
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Erreur ");
                        alert2.setHeaderText("Echec d'ajout de l'emprunt!");
                        alert2.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, cet emprunt n'existe pas!\nVérifier que l'adhérent,l'ouvrage existent et vérifier la validité des information entrées puis essayez de nouveau!");
                        Stage alertStage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert2.showAndWait();
                        dateretour.setValue(null);
                        dateemp.setValue(null);
                        tfisbn.clear();
                        tfcin.clear();
                        break;
                    case 1:
                        OuvrageDAO.incrementNombreExemplaire(isbn);
                        AdherentDAO.decrementNombreEmpruntAdh(Integer.parseInt(cin));
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setTitle("Succès");
                        alert3.setHeaderText("Emprunt restituer avec succès!");
                        alert3.setContentText("L'emprunt a été restitué avec succès");
                        Stage alertStage3 = (Stage) alert3.getDialogPane().getScene().getWindow();
                        alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert3.showAndWait();
                        tfcin.clear();
                        tfisbn.clear();
                        dateemp.setValue(null);
                        dateretour.setValue(null);
                }

            }

        }


    }



}
