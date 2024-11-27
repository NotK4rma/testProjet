package com.projet.projet.controllers;

import com.projet.projet.adherant.adherent;
import com.projet.projet.bibliothéque.Bibliotheque;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adhController implements Initializable {
    @FXML
    private Button b_ajout;

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_emprunt;

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
    private TableColumn<adherent, String> c_cin;

    @FXML
    private TableColumn<adherent, String> c_id;

    @FXML
    private TableColumn<adherent, String> c_nom;

    @FXML
    private TableColumn<adherent, String> c_prenom;
    @FXML
    private TableView<adherent> t_adh;




    private final SceneMethods editor = new SceneMethods();
    private final String css= "../Styles/RegularStyles.css";
    private final String css2= "../Styles/newWindowStyles.css";

    private Bibliotheque library;

    public void getInstance(Bibliotheque library){
        this.library = library;
    }

   /* private void afficherAdhérent(){




    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        c_id.setCellValueFactory(new PropertyValueFactory<>("nbemprunt"));
        c_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        c_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));



        menuOpen.setOnMouseClicked(e -> editor.translationOpen(slider, hide, menuOpen, menuClose));
        menuClose.setOnMouseClicked(e -> editor.translationClose(slider, hide, menuOpen, menuClose));

        b_close.setOnMouseClicked(e -> editor.exit());
        b_logout.setOnMouseClicked(e -> {
            try {
                editor.deconnect((Stage) b_close.getScene().getWindow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_retour.setOnMouseClicked(e -> {
            try {
                editor.returnHome((Stage) b_logout.getScene().getWindow());
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


        b_ajout.setOnMouseClicked(e-> {
            try {
                editor.switchMiniStage("../ajoutAdhScene.fxml",css2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_remove.setOnMouseClicked(e-> {
            try {
                editor.switchMiniStage("../supprimerAdhScene.fxml",css2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });







    }













}
