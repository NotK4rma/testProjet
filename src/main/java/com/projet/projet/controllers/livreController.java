package com.projet.projet.controllers;

import com.projet.projet.utilsScene.SceneMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class livreController implements Initializable {
    @FXML
    private Button b_adh;

    @FXML
    private Button b_ajout;

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_emprunt;

    @FXML
    private Button b_lib2;

    @FXML
    private Button b_logout;

    @FXML
    private Button b_remove;

    @FXML
    private ImageView b_retour;

    @FXML
    private Button b_tous;

    @FXML
    private AnchorPane background;

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
    private Label titreScene;
    @FXML
    private Rectangle hide;
    @FXML
    private ChoiceBox<String>  choiceRecherche;

    @FXML
    private TableView<?> tablebandedess;

    @FXML
    private TableView<?> tablediction;

    @FXML
    private TableView<?> tablelivre;

    @FXML
    private TableView<?> tablemagazine;

    @FXML
    private TableView<?> tableouv;
    @FXML
    private Label l_recherche;

    private String css= "../Styles/RegularStyles.css";
    private String css2= "../Styles/newWindowStyles.css";

    private SceneMethods editor = new SceneMethods();

    private String[] options = {"Livre", "Dictionnaire", "Magazine", "Bande déssinée"};





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceRecherche.getItems().addAll(options);
        choiceRecherche.setOnAction(this::findSearchBar);

        menuOpen.setOnMouseClicked(e->editor.translationOpen(slider,hide,menuOpen,menuClose));
        menuClose.setOnMouseClicked(e->editor.translationClose(slider,hide,menuOpen,menuClose));

        b_close.setOnMouseClicked(e->editor.exit());
        b_logout.setOnMouseClicked(e-> {
            try {
                editor.deconnect((Stage) b_close.getScene().getWindow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_retour.setOnMouseClicked(e-> {
            try {
                editor.returnHome((Stage) b_logout.getScene().getWindow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

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




        b_lib2.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../librScene.fxml",css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        b_ajout.setOnMouseClicked(e-> {
            try {
                editor.switchMiniStage("../ajoutLivScene.fxml",css2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_remove.setOnMouseClicked(e-> {
            try {
                editor.switchMiniStage("../supprimerOuvScene.fxml",css2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });









    }






    private void findSearchBar(ActionEvent event){
        l_recherche.setVisible(false);
        switch (choiceRecherche.getValue()){
            case "Livre":
                tablediction.setVisible(false);
                tablebandedess.setVisible(false);
                tablemagazine.setVisible(false);
                tableouv.setVisible(false);
                tablelivre.setVisible(true);
                break;
            case "Dictionnaire":
                tablediction.setVisible(true);
                tablebandedess.setVisible(false);
                tablemagazine.setVisible(false);
                tableouv.setVisible(false);
                tablelivre.setVisible(false);
                break;
            case "Magazine":
                tablediction.setVisible(false);
                tablebandedess.setVisible(false);
                tablemagazine.setVisible(true);
                tableouv.setVisible(false);
                tablelivre.setVisible(false);
                break;
            case "Bande déssinée":
                tablediction.setVisible(false);
                tablebandedess.setVisible(true);
                tablemagazine.setVisible(false);
                tableouv.setVisible(false);
                tablelivre.setVisible(false);
                break;
            default:
                break;

        }

    }







}
