package com.projet.projet.utilsScene;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SceneMethods {
    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fermer l'application");
        alert.setHeaderText("Quiter l'appliaction ?");
        alert.setContentText("En confirmant, vous allez quitter l'application");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResource("/com/projet/projet/Images/logo.png").toExternalForm()));
        if(alert.showAndWait().get()== ButtonType.OK){
            System.exit(0);
        }
    }



    public void deconnect(Stage stage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("Terminer cette session ?");
        alert.setContentText("Vous allez etres déconnecté.\nVous serez rediriger vers le menu Log In");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResource("/com/projet/projet/Images/logo.png").toExternalForm()));
        if(alert.showAndWait().get()== ButtonType.OK){
            switchScene(stage, "../loginScene.fxml","../Styles/signInUpStyles.css");
        }
    }


    public void returnHome(Stage stage)throws IOException{
        switchScene(stage,"../homeScene.fxml", "../Styles/RegularStyles.css");
    }



    public void switchScene(Stage stage,String url, String css)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene= new Scene(root);
        scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void translationClose(AnchorPane p, Rectangle rect, Label open, Label close){
        TranslateTransition slide = new TranslateTransition();
        FadeTransition fadeOut = new FadeTransition();
        fadeOut.setDuration(Duration.millis(300));
        fadeOut.setNode(rect);
        fadeOut.setFromValue(0.8);
        slide.setDuration(Duration.millis(300));
        slide.setNode(p);
        slide.setToX(-280);
        fadeOut.setToValue(0);

        slide.play();
        fadeOut.play();
        p.setTranslateX(0);
        slide.setOnFinished(e->{
            open.setVisible(true);
            close.setVisible(false);
        });

    }

    public void translationOpen(AnchorPane p, Rectangle rect, Label open, Label close){
        TranslateTransition slide = new TranslateTransition();
        FadeTransition fadeIn = new FadeTransition();
        fadeIn.setDuration(Duration.millis(300));
        fadeIn.setNode(rect);
        fadeIn.setFromValue(0);

        slide.setDuration(Duration.millis(300));
        slide.setNode(p);
        slide.setToX(0);
        fadeIn.setToValue(0.8);

        fadeIn.play();
        slide.play();
        p.setTranslateX(-280);
        slide.setOnFinished(e->{
            open.setVisible(false);
            close.setVisible(true);
        });

    }



}