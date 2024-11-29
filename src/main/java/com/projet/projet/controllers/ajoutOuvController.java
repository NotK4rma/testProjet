package com.projet.projet.controllers;

import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ajoutOuvController implements Initializable {
    @FXML
    private ChoiceBox<String> choix_type;

    @FXML
    private DatePicker datepub;

    @FXML
    private HBox hbox_dict;

    @FXML
    private HBox hbox_illustarteur;

    @FXML
    private HBox hbox_livre;

    @FXML
    private HBox hbox_magazaine;

    @FXML
    private TextField tfauteur;

    @FXML
    private TextField tfgenre;

    @FXML
    private TextField tfillust;

    @FXML
    private TextField tfisbn;

    @FXML
    private TextField tflangue;

    @FXML
    private TextField tfnbexemp;

    @FXML
    private TextField tfnbmot;

    @FXML
    private TextField tfprix;

    @FXML
    private TextField tfseller;

    @FXML
    private TextField tftitre;
    @FXML
    private Button b_ajout;

    @FXML
    private Button b_supprimer;

    @FXML
    private TextField tfSupprimerISBN;


    final private String[] options= {"Livre","Magazine", "Bande déssinée", "Dictionnaire"};








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choix_type.getItems().addAll(options);
        choix_type.setOnAction(e->showHiddenFields(e,b_ajout));

        b_ajout.setOnMouseClicked(e->ajouterOuvrage());
        b_supprimer.setOnMouseClicked(e->supprmerOuvrage());







    }


    private void ajouterOuvrage(){
        if(tfauteur.getText().isBlank() || tfisbn.getText().isBlank() || tfprix.getText().isBlank() ||tfseller.getText().isBlank() ||tfnbexemp.getText().isBlank() || choix_type.getValue()==null || !SceneMethods.isDouble(tfprix.getText()) || !SceneMethods.isBoolean(tfseller.getText()) || !SceneMethods.isInteger(tfnbexemp.getText()) || !SceneMethods.isInteger(tfisbn.getText())){
            System.out.println("field not filled");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vérifier les champs de texte!");
            alert.setContentText("Prière de remplir tous les champs disponibles.");
            alert.showAndWait();


        }
        else {
            String type = choix_type.getValue();
            boolean empty = true ;
            boolean valid = true;
            String auteur="";
            String genre="";
            LocalDate date_p=null;
            String illust="";
            String nbmot="";
            String lang="";
            switch (type){
                case "Livre":
                    auteur = tfauteur.getText();
                    genre = tfgenre.getText();
                    empty = auteur.isBlank() || genre.isBlank();
                    break;
                case "Magazine":
                    date_p = datepub.getValue();
                    empty = date_p==null;
                    break;
                case "Bande déssinée":
                    illust = tfillust.getText();
                    empty = illust.isBlank();
                    break;
                case "Dictionnaire":
                    nbmot = tfnbmot.getText();
                    lang = tflangue.getText();
                    empty = nbmot.isBlank() || lang.isBlank();
                    valid = SceneMethods.isInteger(nbmot);
                    break;
                default:
                    break;

            }

            if(empty || !valid){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vérifier les champs de texte spécifiques au type de l'ouvrage!");
                alert.setContentText("Prière de remplir tous les champs disponibles pour le type de l'ouvrage.");
                alert.showAndWait();


            }
            else{
                String titre=tftitre.getText();
                String isbn = tfisbn.getText();
                boolean bSeller = Boolean.parseBoolean(tfseller.getText());
                double prix = Double.parseDouble(tfprix.getText());
                int nbexp = Integer.parseInt(tfnbexemp.getText());
                int nbmot_int = Integer.parseInt(nbmot);
                if(OuvrageDAO.existeOuvrage(isbn)>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur 1");
                    alert.setHeaderText("Echec d'ajout de l'ouvrage!");
                    alert.setContentText("Un y'a un erreur lors de verification de l'etat de l'ouvrage, impossible d'ajouter l'ouvrage, essayez de nouveau");
                    alert.showAndWait();
                }
                else {
                    int res = OuvrageDAO.saveOuvrage(titre, isbn, prix, bSeller, nbexp, auteur, genre, illust, date_p, lang, nbmot_int);
                    if (res == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Ajout avec succès!");
                        alert.setContentText("L'ouvrage a été ajouté avec succès");
                        alert.showAndWait();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur 2");
                        alert.setHeaderText("Echec d'ajout de l'ouvrage!");
                        alert.setContentText("Un y'a un erreur inconnu, impossible d'ajouter l'ouvrage, essayez de nouveau");
                        alert.showAndWait();
                    }
                }


            }

        }




    }


    private void supprmerOuvrage(){
        String isbn = tfSupprimerISBN.getText();
        if(isbn.isBlank() || SceneMethods.isInteger(isbn)){
            SceneMethods.alertErrorWindow();
        }
        else {
            int res = OuvrageDAO.deleteOuvrage(isbn);
            switch (res){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("introuvable");
                    alert.setHeaderText("Echec de suppression de l'ouvrage!");
                    alert.setContentText("L'ouvrage n'existe pas");
                    alert.showAndWait();
                    break;
                case -1:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Erreur 2");
                    alert2.setHeaderText("Echec de suppression de l'ouvrage!");
                    alert2.setContentText("Un y'a un erreur inconnu, impossible de supprimer l'ouvrage, essayez de nouveau");
                    alert2.showAndWait();
                    break;
                case 1:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Succès");
                    alert3.setHeaderText("Suppression avec succès!");
                    alert3.setContentText("L'ouvrage a été supprimé avec succès");
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }
        }

    }





    private void animatePage(Button b){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.millis(300));
        slide.setNode(b);

        slide.setByY(70);
        slide.play();

    }

    private void showHiddenFields(ActionEvent e,Button b){
        animatePage(b);
        switch(choix_type.getValue()){
            case "Livre":
                hbox_livre.setVisible(true);
                hbox_dict.setVisible(false);
                hbox_illustarteur.setVisible(false);
                hbox_magazaine.setVisible(false);
                break;
            case "Magazine":
                hbox_livre.setVisible(false);
                hbox_dict.setVisible(false);
                hbox_illustarteur.setVisible(false);
                hbox_magazaine.setVisible(true);
                break;
            case "Bande déssinée":
                hbox_livre.setVisible(false);
                hbox_dict.setVisible(false);
                hbox_illustarteur.setVisible(true);
                hbox_magazaine.setVisible(false);
                break;
            case "Dictionnaire":
                hbox_livre.setVisible(false);
                hbox_dict.setVisible(true);
                hbox_illustarteur.setVisible(false);
                hbox_magazaine.setVisible(false);
                break;
            default:
                break;
        }

    }



}
