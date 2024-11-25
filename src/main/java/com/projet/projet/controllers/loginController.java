package com.projet.projet.controllers;

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
}
