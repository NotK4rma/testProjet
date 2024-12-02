package com.projet.projet.controllers;

import com.projet.projet.dataManagement.AdherentDAO;
import com.projet.projet.dataManagement.LibrarianDAO;
import com.projet.projet.personne.adherent;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class libController implements Initializable {

    @FXML
    private Button b_adh;

    @FXML
    private Button b_ajout;

    @FXML
    private ImageView b_close;

    @FXML
    private Button b_emprunt;

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
    private Rectangle hide;

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
    private Label l_nomlib;


    @FXML
    private TableView<String> t_libr;


    @FXML
    private TableColumn<String, String> c_username;

    @FXML
    private ImageView refreshTables;

    @FXML
    private TextField rechercheLib;


    private SceneMethods editor = new SceneMethods();
    private static String libName;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(loginController.getLibName()==null) {
            libName = signupController.getLibName();
        }else{
            libName =loginController.getLibName();
        }

        Tooltip tooltip = new Tooltip("Effacer le contenu du tableau");
        Tooltip.install(refreshTables, tooltip);


        refreshTables.setOnMouseClicked(e->t_libr.getItems().clear());

        l_nomlib.setWrapText(true);
        l_nomlib.setText("Bienvenue, "+libName+"!");

        b_tous.setOnMouseClicked(e->afficherBibliothecaire());

        b_ajout.setOnMouseClicked(e->{

            try {
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Validation");
                alert.setHeaderText("Ajouter Bibliothecaire ?");
                alert.setContentText("Le compte créer aura accès à la base de donnée!\nVoulez vous l'ajouter?");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                if (alert.showAndWait().get()==ButtonType.OK) {
                    editor.switchMiniStage("../ajoutBibScene.fxml","../Styles/newWindowStyles.css");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        b_remove.setOnMouseClicked(e->{

            try {
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Validation");
                alert.setHeaderText("Supprimer Bibliothecaire ?");
                alert.setContentText("Ce compte n'aura plus d'accès à la base de donnée!\nVoulez vous le supprimer?");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                if (alert.showAndWait().get()==ButtonType.OK) {
                    editor.switchMiniStage("../SupprimerBibScene.fxml","../Styles/newWindowStyles.css");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        c_username.setCellValueFactory(data->
                new SimpleStringProperty(SceneMethods.capitalizeFirstLetter(data.getValue()))
        );

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


        rechercheLib.setOnAction(e->chercherBibByUsername());




    }



    private void afficherBibliothecaire(){
        List<String> Lbib = LibrarianDAO.afficherLibrs();
        ObservableList<String> observableLbib = FXCollections.observableArrayList(Lbib);
        t_libr.setItems(observableLbib);

    }

    private void chercherBibByUsername(){
        String un = rechercheLib.getText();
        t_libr.getItems().clear();
        String lib = LibrarianDAO.chercherLibrs(un);
        if(lib.isEmpty()){
            System.out.println("n'existe pas");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Bibliothecaire Introuvable!");
            alert.setContentText("Cet Bibliothecaire n'existe pas!\nEnregistrez le en clickant ajouter Bibliothecaire ou en utilisant la page Sign Up.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert.showAndWait();
        }
        else {
            ObservableList<String> observableLLib = FXCollections.observableArrayList(lib);
            System.out.println(observableLLib);
            t_libr.setItems(observableLLib);
        }
        rechercheLib.clear();

    }


}
