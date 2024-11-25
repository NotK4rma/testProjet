package com.projet.projet.controllers;

import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    SceneMethods editor = new SceneMethods();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_close.setOnMouseClicked(e->editor.exit());

        b_signup.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage) b_close.getScene().getWindow(),"../homeScene.fxml", "../Styles/RegularStyles.css");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


    }
}
