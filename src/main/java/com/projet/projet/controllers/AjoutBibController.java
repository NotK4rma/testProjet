package com.projet.projet.controllers;

import com.projet.projet.dataManagement.LibrarianDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AjoutBibController implements Initializable {
    @FXML
    private Button b_ajout;

    @FXML
    private PasswordField tfconfirm;

    @FXML
    private PasswordField tfmdp;

    @FXML
    private TextField tfuser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_ajout.setOnMouseClicked(e-> {
            try {
                signUp();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



    }


    private void signUp() throws IOException {
        String un = tfuser.getText().trim();
        String pw = tfmdp.getText();
        String c_pw = tfconfirm.getText();
        if(un.isBlank() || pw.isBlank() || c_pw.isBlank()){
            SceneMethods.alertErrorWindow();
        }
        else {
            if(pw.equals(c_pw)){
                int res = LibrarianDAO.saveLibrarian(un,pw);
                switch (res){
                    case 0:
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Connexion avec succès!");
                        alert.setContentText("Bienvenue, vous serez derige vers le menu home!");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        tfconfirm.clear();
                        tfmdp.clear();
                        tfuser.clear();



                        break;
                    case -1:
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Erreur 1");
                        alert2.setHeaderText("Echec d'ajout du bibliothecaire!");
                        alert2.setContentText("Un y'a un erreur lors de l'ajout du bibliothecaire, ce compte existe deja, essayez de nouveau!");
                        Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                        alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert2.showAndWait();
                        tfmdp.clear();
                        tfconfirm.clear();
                        tfuser.clear();
                        break;
                    case -2:
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setTitle("Erreur 2");
                        alert3.setHeaderText("Echec d'ajout du bibliothecaire!");
                        alert3.setContentText("Un y'a un erreur lors de verification de l'etat du bibliothecaire,  essayez de nouveau!");
                        Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                        alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert3.showAndWait();
                        tfconfirm.clear();
                        tfuser.clear();
                        tfmdp.clear();
                        break;
                    default:
                        break;

                }

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vérifier Les mots de passe!");
                alert.setContentText("Prière de vérifier que les deux mot de passe entré sont identique.");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                alert.showAndWait();
                tfmdp.clear();
                tfconfirm.clear();


            }
        }


    }



}
