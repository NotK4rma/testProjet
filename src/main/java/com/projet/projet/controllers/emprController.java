package com.projet.projet.controllers;

import com.projet.projet.pret.prets;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class emprController implements Initializable {
    @FXML
    private Button b_adh;

    @FXML
    private Button b_ajout;

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_lib2;

    @FXML
    private Button b_livre;

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
    private ChoiceBox<String> choiceRecherche;

    @FXML
    private CheckBox emprcourrant;

    @FXML
    private Rectangle hide;

    @FXML
    private Label l_recherche;

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
    TextField tf_isbn;
    @FXML
    TextField tf_cin;
    @FXML
    TextField tf_date;
    @FXML
    private Label l_nomlib;

    @FXML
    private ImageView refreshTables;

    @FXML
    private TableView<prets> t_empr;


    private SceneMethods editor = new SceneMethods();
    private String[] options = {"ISBN du livre", "DATE d'emprunt", "CIN d'adhérent"};
    private String css2= "../Styles/newWindowStyles.css";
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


        choiceRecherche.getItems().addAll(options);
        choiceRecherche.setOnAction(this::findSearchBar);

        Tooltip tooltip = new Tooltip("Effacer le contenu du tableaux");
        Tooltip.install(refreshTables, tooltip);

        refreshTables.setOnMouseClicked(e-> t_empr.getItems().clear());

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
        String css= "../Styles/RegularStyles.css";
        b_adh.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../adhScene.fxml", css);
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




        b_livre.setOnMouseClicked(e-> {
            try {
                editor.switchScene((Stage)b_close.getScene().getWindow(),"../livreScene.fxml",css);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });










    }
    private void findSearchBar(ActionEvent event){
        l_recherche.setVisible(false);
        switch (choiceRecherche.getValue()){
            case "CIN d'adhérent":
                tf_isbn.setVisible(false);
                tf_date.setVisible(false);
                tf_cin.setVisible(true);
                break;
            case "DATE d'emprunt":
                tf_isbn.setVisible(false);
                tf_cin.setVisible(false);
                tf_date.setVisible(true);
                break;
            case "ISBN du livre":
                tf_cin.setVisible(false);
                tf_date.setVisible(false);
                tf_isbn.setVisible(true);
            default:
                break;

        }

    }


}
