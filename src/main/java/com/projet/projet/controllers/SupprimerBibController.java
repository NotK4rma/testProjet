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

import java.net.URL;
import java.util.ResourceBundle;

public class SupprimerBibController implements Initializable {

    @FXML
    private Button b_supprim;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField username;
    private static String libName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(loginController.getLibName()==null) {
            libName = signupController.getLibName();
        }else{
            libName =loginController.getLibName();
        }

        b_supprim.setOnMouseClicked(e->supprimerBibliothecaire());


    }

    private void supprimerBibliothecaire(){
        String un = username.getText().trim();
        String pw = mdp.getText();
        System.out.println(un + "       "+ libName);
        if (!(un.equalsIgnoreCase(libName))) {
            int res = LibrarianDAO.deleteLibr(un,pw);
            switch (res){
                case 1:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("Supression réussie!");
                    alert.setContentText("Le bibliothecaire a été supprimé avec succès!");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    mdp.clear();
                    username.clear();
                    break;
                case 0:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Erreur");
                    alert2.setHeaderText("Echec de suppression du bibliothecaire!");
                    alert2.setContentText("Un y'a un erreur lors de l'ajout du bibliothecaire, ce compte n'existe pas ou le mot de passe est erroné, essayez de nouveau!");
                    Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                    alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert2.showAndWait();
                    mdp.clear();

                    username.clear();
                    break;
                case -1:
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Erreur ");
                    alert3.setHeaderText("Echec de suppression du bibliothecaire!");
                    alert3.setContentText("Un y'a un erreur lors de verification de l'etat du bibliothecaire,  essayez de nouveau!");
                    Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                    alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert3.showAndWait();
                    mdp.clear();
                    username.clear();
                    break;
                default:
                    break;

            }
        }else {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Erreur ");
            alert3.setHeaderText("Echec de suppression du bibliothecaire!");
            alert3.setContentText("Vous ne pouvez pas supprimer le bibliothecaire en cours d'utilisation,  essayez de nouveau!");
            Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
            alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert3.showAndWait();
            mdp.clear();
            username.clear();


        }


    }



}
