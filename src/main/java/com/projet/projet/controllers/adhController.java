package com.projet.projet.controllers;

import com.projet.projet.personne.adherent;

import com.projet.projet.dataManagement.AdherentDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<adherent, String> c_nbemp;

    @FXML
    private TableColumn<adherent, String> c_nom;

    @FXML
    private TableColumn<adherent, String> c_prenom;
    @FXML
    private TableView<adherent> t_adh;

    @FXML
    private Label l_nomlib;
    @FXML
    private ImageView refreshTables;

    @FXML
    private TextField recherchecin;


    private final SceneMethods editor = new SceneMethods();
    private final String css= "../Styles/RegularStyles.css";
    private final String css2= "../Styles/newWindowStyles.css";
    private static String libName;



   private void afficherAdherent(){
       List<adherent> Ladh = AdherentDAO.afficherAdherant();

       ObservableList<adherent> observableLadh = FXCollections.observableArrayList(Ladh);
       t_adh.setItems(observableLadh);



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(loginController.getLibName()==null) {
            libName = signupController.getLibName();
        }else{
            libName =loginController.getLibName();
        }

        l_nomlib.setWrapText(true);
        l_nomlib.setText("Bienvenue, "+libName+"!");

        Tooltip tooltip = new Tooltip("Effacer le contenu du tableau");
        Tooltip.install(refreshTables, tooltip);

        refreshTables.setOnMouseClicked(e-> t_adh.getItems().clear());

        c_nbemp.setCellValueFactory(new PropertyValueFactory<>("nbemprunt"));
        c_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));


        c_nom.setCellValueFactory(data->
                new SimpleStringProperty(SceneMethods.capitalizeFirstLetter(data.getValue().getNom()))
        );


        c_prenom.setCellValueFactory(data->
                new SimpleStringProperty(SceneMethods.capitalizeFirstLetter(data.getValue().getPrenom()))
        );

        b_tous.setOnMouseClicked(e->afficherAdherent());

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


        recherchecin.setOnAction(e->chercherAdherentByCIN());





    }

    private void chercherAdherentByCIN(){
        int cin = Integer.parseInt(recherchecin.getText());
        t_adh.getItems().clear();
        List<adherent> Ladh = AdherentDAO.rechercheAdhCin(cin);
        if(Ladh.size()==0){
            System.out.println("n'existe pas");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Adhérent Introuvable!");
            alert.setContentText("Cet adhérent n'existe pas!\nEnregistrez le en clickant ajouter adhérent.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert.showAndWait();
        }
        else {
            ObservableList<adherent> observableLadh = FXCollections.observableArrayList(Ladh);
            System.out.println(observableLadh);
            t_adh.setItems(observableLadh);
        }
        recherchecin.clear();

    }














}
