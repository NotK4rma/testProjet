package com.projet.projet.controllers;

import com.projet.projet.utilsScene.SceneMethods;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
    @FXML
    private Pane adhinfo;

    @FXML
    private Button b_adh;

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_emprunt;

    @FXML
    private Button b_lib;

    @FXML
    private Button b_livre;

    @FXML
    private Button b_logout;

    @FXML
    private AnchorPane bg_slide;

    @FXML
    private Pane bookinfo;

    @FXML
    private Pane librarianinfo;

    @FXML
    private Label menuClose;

    @FXML
    private Label menuOpen;

    @FXML
    private Pane menubardown;

    @FXML
    private AnchorPane menubarup;

    @FXML
    private AnchorPane slider;

    @FXML
    private Label l_nomlib;
    @FXML
    private Label l_infoLib;

    @FXML
    private Label l_infobooks;

    @FXML
    private Label l_inforeader;

    private SceneMethods editor = new SceneMethods();
    private static String libName;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(loginController.getLibName()==null) {
            libName = signupController.getLibName();
        }else{
            libName =loginController.getLibName();
        }
        l_nomlib.setWrapText(true);
        l_nomlib.setText("Bienvenue, "+libName+"!");

        


        b_close.setOnMouseClicked(e->editor.exit());
        b_logout.setOnMouseClicked(e-> {
            try {
                editor.deconnect((Stage) b_close.getScene().getWindow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bookinfo.setOnMouseEntered(e->hoverInAnimation(bookinfo));
        bookinfo.setOnMouseExited(e->hoverOutAnimation(bookinfo));
        addHoverShadowEffect(bookinfo);



        adhinfo.setOnMouseEntered(e->hoverInAnimation(adhinfo));
        adhinfo.setOnMouseExited(e->hoverOutAnimation(adhinfo));
        addHoverShadowEffect(adhinfo);

        librarianinfo.setOnMouseEntered(e->hoverInAnimation(librarianinfo));
        librarianinfo.setOnMouseExited(e->hoverOutAnimation(librarianinfo));
        addHoverShadowEffect(librarianinfo);


        menuOpen.setOnMouseClicked(event->{

            TranslateTransition slide = new TranslateTransition();
            TranslateTransition slide2 = new TranslateTransition();


            slide.setDuration(Duration.millis(300));
            slide2.setDuration(Duration.millis(300));

            slide.setNode(slider);
            slide2.setNode(bg_slide);


            slide.setToX(0);
            slide2.setToX(282);

            slide.play();
            slide2.play();

            slider.setTranslateX(-282);
            bg_slide.setTranslateX(0);
            slide.setOnFinished(e->{
                menuOpen.setVisible(false);
                menuClose.setVisible(true);
            });




        });

        menuClose.setOnMouseClicked(event->{

            TranslateTransition slide = new TranslateTransition();
            TranslateTransition slide2 = new TranslateTransition();


            slide.setDuration(Duration.millis(300));
            slide2.setDuration(Duration.millis(300));

            slide.setNode(slider);
            slide2.setNode(bg_slide);


            slide.setToX(-282);
            slide2.setToX(0);

            slide.play();
            slide2.play();

            slider.setTranslateX(0);
            bg_slide.setTranslateX(282);
            slide.setOnFinished(e->{
                menuOpen.setVisible(true);
                menuClose.setVisible(false);
            });




        });



        String css= "../Styles/RegularStyles.css";

        b_adh.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../adhScene.fxml", css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_emprunt.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../emprScene.fxml",css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_livre.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../livreScene.fxml",css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        b_lib.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../librScene.fxml",css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });









    }

    private void hoverInAnimation(Pane p){
        ScaleTransition up = new ScaleTransition();
        up.setDuration(Duration.millis(300));
        up.setNode(p);
        up.setToX(1.05);
        up.setToY(1.05);
        up.play();

    }
    private void hoverOutAnimation(Pane p){
        ScaleTransition down = new ScaleTransition();
        down.setDuration(Duration.millis(300));
        down.setNode(p);
        down.setToX(1);
        down.setToY(1);
        down.play();

    }


    public static void addHoverShadowEffect(Pane pane) {
        // Create a DropShadow effect
        DropShadow shadow = new DropShadow();
        shadow.setRadius(0); // Start with no shadow
        shadow.setColor(Color.GRAY);
        shadow.setOffsetX(0);
        shadow.setOffsetY(0);

        // Apply the shadow effect to the Pane
        pane.setEffect(shadow);

        // Create Timeline for hover-in animation
        Timeline hoverIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 0)),
                new KeyFrame(Duration.millis(200), new KeyValue(shadow.radiusProperty(), 40))
        );

        // Create Timeline for hover-out animation
        Timeline hoverOut = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 40)),
                new KeyFrame(Duration.millis(200), new KeyValue(shadow.radiusProperty(), 0))
        );

        // Add event listeners for hover effects
        pane.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> hoverIn.play());
        pane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> hoverOut.play());
    }

    /*public static void getLibName(String name){
        libName = name;
    }*/


}
