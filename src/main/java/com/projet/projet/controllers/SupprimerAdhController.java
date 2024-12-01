package com.projet.projet.controllers;

import com.projet.projet.dataManagement.AdherentDAO;
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

public class SupprimerAdhController implements Initializable {
    @FXML
    private Button b_supprimer;

    @FXML
    private TextField tfSupprimerCin;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_supprimer.setOnMouseClicked(e->supprimerAdherent());


    }


    private void supprimerAdherent(){
        String cin = tfSupprimerCin.getText();
        if(cin.isBlank() || !SceneMethods.isInteger(cin)){
            SceneMethods.alertErrorWindow();
        }
        else{
            int res = AdherentDAO.deleteAdherent(Integer.parseInt(cin));
            switch(res){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("introuvable");
                    alert.setHeaderText("Echec de suppression de l'aherent!");
                    alert.setContentText("L'adherent n'existe pas");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    break;
                case -1:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Erreur ");
                    alert2.setHeaderText("Echec de suppression de l'adherent!");
                    alert2.setContentText("Un y'a un erreur inconnu, impossible de supprimer l'adherent, essayez de nouveau!");
                    Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                    alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert2.showAndWait();
                    break;
                case 1:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Succès");
                    alert3.setHeaderText("Suppression avec succès!");
                    alert3.setContentText("L'adherent a été supprimé avec succès");
                    Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                    alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert3.showAndWait();
                    tfSupprimerCin.clear();
                    break;
                default:
                    break;
            }

        }


    }




}

