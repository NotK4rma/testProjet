package com.projet.projet.controllers;

import com.projet.projet.adherant.adherent;
import com.projet.projet.dataManagement.AdherentDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutSuppAdherantController implements Initializable {
    @FXML
    private Button b_ajout;

    @FXML
    private TextField tfcin;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tfSupprimerCin;

    @FXML
    private Button b_supprimer;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_ajout.setOnMouseClicked(e->ajouterAdherent());
        b_supprimer.setOnMouseClicked(e->supprimerAdherent());



    }

    private void ajouterAdherent(){
           String nom = tfnom.getText();
           String prenom = tfprenom.getText();
           String cin = tfcin.getText();
           if(nom.isBlank() || prenom.isBlank() || cin.isBlank() || !SceneMethods.isInteger(cin)){
               SceneMethods.alertErrorWindow();
           }
           else{
                adherent adh = new adherent(Integer.parseInt(cin),nom,prenom);
                int res = AdherentDAO.saveAdherant(adh);
                switch (res){
                    case 0:
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Ajout avec succès!");
                        alert.setContentText("L'ouvrage a été ajouté avec succès");
                        alert.showAndWait();
                        break;
                    case -1:
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Erreur ");
                        alert2.setHeaderText("Echec d'ajout de l'adherent!");
                        alert2.setContentText("Un y'a un erreur lors de l'ajout de l'adherent, cet cin existe deja, essayez de nouveau");
                        alert2.showAndWait();
                        break;
                    case -2:
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setTitle("Erreur ");
                        alert3.setHeaderText("Echec d'ajout de l'adherent!");
                        alert3.setContentText("Un y'a un erreur lors de verification de l'etat de l'adherent,  essayez de nouveau");
                        alert3.showAndWait();
                        break;
                    default:
                        break;

                }

           }


    }


    private void supprimerAdherent(){
        String cin = tfSupprimerCin.getText();
        if(cin.isBlank() || SceneMethods.isInteger(cin)){
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
                    alert.showAndWait();
                    break;
                case -1:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Erreur ");
                    alert2.setHeaderText("Echec de suppression de l'adherent!");
                    alert2.setContentText("Un y'a un erreur inconnu, impossible de supprimer l'adherent, essayez de nouveau");
                    alert2.showAndWait();
                    break;
                case 1:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Succès");
                    alert3.setHeaderText("Suppression avec succès!");
                    alert3.setContentText("L'adherent a été supprimé avec succès");
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }

        }


    }






}
