package com.projet.projet.controllers;

import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SupprimerOuvController implements Initializable {

    @FXML
    private Button b_supprimer;

    @FXML
    private TextField tfSupprimerISBN;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            b_supprimer.setOnMouseClicked(e->supprimerOuvrage());


    }





    private void supprimerOuvrage(){
        String isbn = tfSupprimerISBN.getText();
        if(isbn.isBlank() || !SceneMethods.isInteger(isbn)){
            SceneMethods.alertErrorWindow();
        }
        else {
            int res = OuvrageDAO.deleteOuvrage(isbn);
            switch (res){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("introuvable");
                    alert.setHeaderText("Echec de suppression de l'ouvrage!");
                    alert.setContentText("L'ouvrage n'existe pas");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    break;
                case -1:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Erreur ");
                    alert2.setHeaderText("Echec de suppression de l'ouvrage!");
                    alert2.setContentText("Un y'a un erreur inconnu, impossible de supprimer l'ouvrage, essayez de nouveau");
                    Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                    alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert2.showAndWait();
                    break;
                case 1:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Succès");
                    alert3.setHeaderText("Suppression avec succès!");
                    alert3.setContentText("L'ouvrage a été supprimé avec succès");
                    Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                    alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert3.showAndWait();
                    tfSupprimerISBN.clear();
                    break;
                default:
                    break;
            }
        }

    }





}
