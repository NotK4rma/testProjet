package com.projet.projet.controllers;

import com.projet.projet.dataManagement.LibrarianDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signupController implements Initializable {


    @FXML
    private ImageView b_close;

    @FXML
    private Button b_signup;

    @FXML
    private PasswordField conf_mdp;

    @FXML
    private AnchorPane container;

    @FXML
    private PasswordField mdp;

    @FXML
    private Label title;

    @FXML
    private TextField username;

    private SceneMethods editor = new SceneMethods();
    private static String libName=null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_close.setOnMouseClicked(e->editor.exit());

        b_signup.setOnMouseClicked(e-> {
            try {
                signUp();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


    }

    private void signUp() throws IOException {
        String un = username.getText().trim();
        String pw = mdp.getText();
        String c_pw = conf_mdp.getText();
        if(un.isBlank() || pw.isBlank() || c_pw.isBlank()){
            SceneMethods.alertErrorWindow();
        }
        else {
            if(pw.equals(c_pw)){
                int res = LibrarianDAO.saveLibrarian(un,pw);
                switch (res){
                    case 0:
                        libName=SceneMethods.capitalizeFirstLetter(un);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Connexion avec succès!");
                        alert.setContentText("Bienvenue, vous serez derige vers le menu home!");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                                                                                                                                                                /*FXMLLoader loader = new FXMLLoader(getClass().getResource("../homeScene.fxml"));
                                                                                                                                                                Scene scene = new Scene(loader.load());
                                                                                                                                                                scene.getStylesheets().add(getClass().getResource("../Styles/RegularStyles.css").toExternalForm());
                                                                                                                                                                homeController controller = loader.getController();
                                                                                                                                                                Stage stage = (Stage)b_signup.getScene().getWindow();
                                                                                                                                                                stage.setScene(scene);
                                                                                                                                                                stage.show();*/
                        editor.switchScene((Stage) b_close.getScene().getWindow(),"../homeScene.fxml", "../Styles/RegularStyles.css");


                        break;
                    case -1:
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Erreur 1");
                        alert2.setHeaderText("Echec d'ajout du bibliothecaire!");
                        alert2.setContentText("Un y'a un erreur lors de l'ajout du bibliothecaire, cet compte existe deja, essayez de nouveau");
                        Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                        alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert2.showAndWait();
                        mdp.clear();
                        conf_mdp.clear();
                        username.clear();
                        break;
                    case -2:
                        Alert alert3 = new Alert(Alert.AlertType.ERROR);
                        alert3.setTitle("Erreur 2");
                        alert3.setHeaderText("Echec d'ajout du bibliothecaire!");
                        alert3.setContentText("Un y'a un erreur lors de verification de l'etat du bibliothecaire,  essayez de nouveau");
                        Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                        alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert3.showAndWait();
                        mdp.clear();
                        conf_mdp.clear();
                        username.clear();
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
                mdp.clear();
                conf_mdp.clear();


            }
        }


    }

    public static String getLibName(){
        return libName;
    }

    public static void revokeLibName(){
        libName=null;
    }


}
