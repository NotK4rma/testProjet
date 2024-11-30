package com.projet.projet.controllers;

import com.projet.projet.adherant.adherent;
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

public class AjoutSuppAdherantController implements Initializable {
    @FXML
    private Button b_ajout;

    @FXML
    private TextField tfcin;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_ajout.setOnMouseClicked(e->ajouterAdherent());




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
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        break;
                    case -1:
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Erreur ");
                        alert2.setHeaderText("Echec d'ajout de l'adherent!");
                        alert2.setContentText("Un y'a un erreur lors de l'ajout de l'adherent, cet cin existe deja, essayez de nouveau");
                        Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                        alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert2.showAndWait();
                        break;
                    case -2:
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setTitle("Erreur ");
                        alert3.setHeaderText("Echec d'ajout de l'adherent!");
                        alert3.setContentText("Un y'a un erreur lors de verification de l'etat de l'adherent,  essayez de nouveau");
                        Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                        alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert3.showAndWait();
                        break;
                    default:
                        break;

                }

           }


    }









}
