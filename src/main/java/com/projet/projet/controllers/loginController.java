package com.projet.projet.controllers;

import com.projet.projet.dataManagement.LibrarianDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_login;

    @FXML
    private AnchorPane container;

    @FXML
    private Hyperlink hyper;

    @FXML
    private PasswordField mdp;

    @FXML
    private Label nouv;

    @FXML
    private Label title;

    @FXML
    private TextField username;

    private SceneMethods editor = new SceneMethods();
    private static String libName=null;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_close.setOnMouseClicked(e->editor.exit());

        hyper.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../signupScene.fxml", "../Styles/signInUpStyles.css");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_login.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../homeScene.fxml","../Styles/RegularStyles.css");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }


    private void logIn() throws IOException {
        String un = username.getText();
        String pw = mdp.getText();
        if(un.isBlank() || pw.isBlank() ){
            SceneMethods.alertErrorWindow();
        }
        else {
            boolean res = LibrarianDAO.getLibr(un,pw);
            if (!res){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur ");
                alert.setHeaderText("Echec de connexion!");
                alert.setContentText("Un y'avait une erreur lors de la connexion ou les information entrées sont erronées , essayez de nouveau");
                alert.showAndWait();
                mdp.clear();
                username.clear();

            }
            else{
                libName=un;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Ajout avec succès!");
                alert.setContentText("L'ouvrage a été ajouté avec succès");
                alert.showAndWait();
                editor.switchScene((Stage) b_close.getScene().getWindow(),"../homeScene.fxml", "../Styles/RegularStyles.css");

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
