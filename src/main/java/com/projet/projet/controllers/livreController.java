package com.projet.projet.controllers;

import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.ouvrage.*;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.util.List;
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
    private TableColumn<livre, String> c_auteur;

    @FXML
    private TableColumn<ouvrage, String> c_best;

    @FXML
    private TableColumn<magazine, LocalDate> c_datepub;

    @FXML
    private TableColumn<ouvrage,String> c_dispo;

    @FXML
    private TableColumn<livre,String> c_genre;

    @FXML
    private TableColumn<bandeDessinee,String> c_illus;

    @FXML
    private TableColumn<ouvrage,String> c_isbn;

    @FXML
    private TableColumn<dictionnaire,String> c_lang;

    @FXML
    private TableColumn<dictionnaire,String> c_nbmot;

    @FXML
    private TableColumn<ouvrage,String> c_prix;

    @FXML
    private TableColumn<ouvrage,String> c_titre;

    @FXML
    private TableColumn<livre, String> c_bestLivre;

    @FXML
    private TableColumn<bandeDessinee, String> c_bestbd;

    @FXML
    private TableColumn<dictionnaire, String> c_bestdict;

    @FXML
    private TableColumn<magazine, String> c_bestmaga;

    @FXML
    private TableColumn<livre, String> c_dispoLivre;

    @FXML
    private TableColumn<bandeDessinee, String> c_dispobd;

    @FXML
    private TableColumn<dictionnaire, String> c_dispodict;

    @FXML
    private TableColumn<magazine, String> c_dispomaga;


    @FXML
    private TableColumn<livre, String> c_isbnLivre;

    @FXML
    private TableColumn<bandeDessinee, String> c_isbnbd;

    @FXML
    private TableColumn<dictionnaire, String> c_isbndict;

    @FXML
    private TableColumn<magazine, String> c_isbnmaga;


    @FXML
    private TableColumn<livre, String> c_prixLivre;

    @FXML
    private TableColumn<bandeDessinee, String> c_prixbd;

    @FXML
    private TableColumn<dictionnaire, String> c_prixdict;

    @FXML
    private TableColumn<magazine, String> c_prixmaga;



    @FXML
    private TableColumn<livre, String> c_titreLivre;

    @FXML
    private TableColumn<bandeDessinee, String> c_titrebd;

    @FXML
    private TableColumn<dictionnaire, String> c_titredict;

    @FXML
    private TableColumn<magazine, String> c_titremaga;


    @FXML
    private TableView<ouvrage> tableouv;
    @FXML
    private TableView<livre> tlivre;

    @FXML
    private TableView<magazine> tmagazine;

    @FXML
    private TableView<bandeDessinee> tbandedess;

    @FXML
    private TableView<dictionnaire> tdict;


    @FXML
    private Label l_recherche;

    @FXML
    private Label l_nomlib;

    @FXML
    private ImageView refreshTables;
    @FXML
    private TextField rechercheisbn;


    private String css= "../Styles/RegularStyles.css";
    private String css2= "../Styles/newWindowStyles.css";

    private SceneMethods editor = new SceneMethods();

    private String[] options = {"Livre", "Dictionnaire", "Magazine", "Bande déssinée"};
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

        Tooltip tooltip = new Tooltip("Effacer le contenu des tableaux et revenir à la table d'ouvrage");
        Tooltip.install(refreshTables, tooltip);

        refreshTables.setOnMouseClicked(e->actualiseTables());


        c_lang.setCellValueFactory(new PropertyValueFactory<>("langue"));


        c_nbmot.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNombreMots()))
        );;


        c_illus.setCellValueFactory(new PropertyValueFactory<>("illustrateur"));


        c_datepub.setCellValueFactory(new PropertyValueFactory<>("datePublication"));


        c_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));


        c_auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));


        c_titre.setCellValueFactory(new PropertyValueFactory<>("title"));


        c_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));


        c_prix.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix()))
        );

        c_best.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isBestseller() ? "Oui" : "Non")
        );;


        c_dispo.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNbexemplaire()))
        );


        c_bestLivre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isBestseller() ? "Oui" : "Non")
                    );;
        c_bestbd.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isBestseller() ? "Oui" : "Non")
        );


        c_bestdict.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isBestseller() ? "Oui" : "Non")
        );


        c_bestmaga.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isBestseller() ? "Oui" : "Non")
        );


        c_dispoLivre.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNbexemplaire()))
        );

        c_dispobd.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNbexemplaire()))
        );

        c_dispodict.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNbexemplaire()))
        );

        c_dispomaga.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNbexemplaire()))
        );

        c_isbnLivre.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIsbn()))
        );

        c_isbnbd.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIsbn()))
        );


        c_isbndict.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIsbn()))
        );

        c_isbnmaga.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getIsbn()))
        );


        c_prixLivre.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix()))
        );

        c_prixbd.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix()))
        );


        c_prixdict.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix()))
        );


        c_prixmaga.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix()))
        );


        c_titreLivre.setCellValueFactory(new PropertyValueFactory<>("title"));


        c_titrebd.setCellValueFactory(new PropertyValueFactory<>("title"));


        c_titredict.setCellValueFactory(new PropertyValueFactory<>("title"));


        c_titremaga.setCellValueFactory(new PropertyValueFactory<>("title"));








        b_tous.setOnMouseClicked(e->{
            if (choiceRecherche.getValue()!=null) {
                switch (choiceRecherche.getValue()){
                    case "Livre":
                        afficherTousLivre();
                        break;
                    case "Dictionnaire":
                        afficherTousDictionnaire();
                        break;
                    case "Magazine":
                        afficherTousMagazine();
                        break;
                    case "Bande déssinée":
                        afficherTousBandeDessinee();
                        break;
                    default:
                        break;

                }
            }
            else {
                afficherTousOuvrage();
            }

        });


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

        rechercheisbn.setOnAction(e->chercherOuvrageByISBN());







    }


    private void actualiseTables(){
        tbandedess.getItems().clear();
        tmagazine.getItems().clear();
        tdict.getItems().clear();
        tlivre.getItems().clear();
        tableouv.getItems().clear();
        tableouv.setVisible(true);
        tbandedess.setVisible(false);
        tmagazine.setVisible(false);
        tdict.setVisible(false);
        tlivre.setVisible(false);
        l_recherche.setVisible(true);
        choiceRecherche.setValue(null);


    }


    private void chercherOuvrageByISBN(){
        String isbn = rechercheisbn.getText();
        actualiseTables();
        List<ouvrage> Louv = OuvrageDAO.chercherOuvrageISBN(isbn);
        if(Louv.size()==0){
            System.out.println("n'existe pas");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Ouvrage Introuvable!");
            alert.setContentText("Cet ouvrage n'existe pas!\nEnregistrez le en clickant ajouter ouvrage.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert.showAndWait();
        }
        else {
            ObservableList<ouvrage> observableLouv = FXCollections.observableArrayList(Louv);
            System.out.println(observableLouv);
            tableouv.setItems(observableLouv);
        }

    }


    private void afficherTousOuvrage(){
        List<ouvrage> Louv = OuvrageDAO.afficherOuvrage();

        ObservableList<ouvrage> observableLouv = FXCollections.observableArrayList(Louv);
        System.out.println(observableLouv);
        tableouv.setItems(observableLouv);


    }

    private void afficherTousLivre(){
        List<livre> Lliv = OuvrageDAO.afficherLivre();
        ObservableList<livre> observableLliv = FXCollections.observableArrayList(Lliv);
        System.out.println(observableLliv);
        tlivre.setItems(observableLliv);


    }

    private void afficherTousBandeDessinee(){
        List<bandeDessinee> Lbd = OuvrageDAO.afficherBd();
        ObservableList<bandeDessinee> observableLbd = FXCollections.observableArrayList(Lbd);
        System.out.println(observableLbd);
        tbandedess.setItems(observableLbd);

    }


    private void afficherTousDictionnaire(){
        List<dictionnaire> Ldict = OuvrageDAO.afficherDictionnaire();
        ObservableList<dictionnaire> observableLdict = FXCollections.observableArrayList(Ldict);
        System.out.println(observableLdict);
        tdict.setItems(observableLdict);

    }


    private void afficherTousMagazine(){
        List<magazine> Lmag = OuvrageDAO.afficherMagazine();
        ObservableList<magazine> observableLmag = FXCollections.observableArrayList(Lmag);
        System.out.println(observableLmag);
        tmagazine.setItems(observableLmag);

    }






    private void findSearchBar(ActionEvent event){
        if (choiceRecherche.getValue()!=null) {
            l_recherche.setVisible(false);
            switch (choiceRecherche.getValue()){
                case "Livre":
                    tableouv.setVisible(false);
                    tbandedess.setVisible(false);
                    tmagazine.setVisible(false);
                    tdict.setVisible(false);
                    tlivre.setVisible(true);
                    break;
                case "Dictionnaire":
                    tableouv.setVisible(false);
                    tbandedess.setVisible(false);
                    tmagazine.setVisible(false);
                    tdict.setVisible(true);
                    tlivre.setVisible(false);
                    break;
                case "Magazine":
                    tableouv.setVisible(false);
                    tbandedess.setVisible(false);
                    tmagazine.setVisible(true);
                    tdict.setVisible(false);
                    tlivre.setVisible(false);
                    break;
                case "Bande déssinée":
                    tableouv.setVisible(false);
                    tbandedess.setVisible(true);
                    tmagazine.setVisible(false);
                    tdict.setVisible(false);
                    tlivre.setVisible(false);
                    break;
                default:
                    break;

            }
        }

    }







}
