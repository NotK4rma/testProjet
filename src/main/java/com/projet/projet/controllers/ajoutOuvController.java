package com.projet.projet.controllers;

import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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




    final private String[] options= {"Livre","Magazine", "Bande déssinée", "Dictionnaire"};
    private int numberAnimation=0;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choix_type.getItems().addAll(options);
        choix_type.setOnAction(e->showHiddenFields(e,b_ajout));

        b_ajout.setOnMouseClicked(e->ajouterOuvrage());






    }


    private void ajouterOuvrage(){
        if(tftitre.getText().isBlank() || tfisbn.getText().isBlank() || tfprix.getText().isBlank() ||tfseller.getText().isBlank() ||tfnbexemp.getText().isBlank() || choix_type.getValue()==null || !SceneMethods.isDouble(tfprix.getText()) || !SceneMethods.isBoolean(tfseller.getText()) || !SceneMethods.isInteger(tfnbexemp.getText()) || !SceneMethods.isInteger(tfisbn.getText())){
            System.out.println("field not filled");
            System.out.println(tftitre.getText()+" "+tfisbn.getText()+" "+tfprix.getText()+" "+tfseller.getText()+" "+tfnbexemp.getText()+" "+ choix_type.getValue());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vérifier les champs de texte!");
            alert.setContentText("Prière de remplir tous les champs disponibles.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
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
                    empty = (auteur.isBlank() || genre.isBlank());
                    break;
                case "Magazine":
                    date_p = datepub.getValue();
                    empty = (date_p==null);
                    break;
                case "Bande déssinée":
                    illust = tfillust.getText();
                    empty = (illust.isBlank());
                    break;
                case "Dictionnaire":
                    nbmot = tfnbmot.getText();
                    lang = tflangue.getText();
                    empty = (nbmot.isBlank() || lang.isBlank());
                    valid = (SceneMethods.isInteger(nbmot));
                    break;
                default:
                    break;

            }

            if(empty || !valid){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vérifier les champs de texte spécifiques au type de l'ouvrage!");
                alert.setContentText("Prière de remplir tous les champs disponibles pour le type de l'ouvrage.");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                alert.showAndWait();


            }
            else{
                String titre=tftitre.getText();
                String isbn = tfisbn.getText();
                boolean bSeller = Boolean.parseBoolean(tfseller.getText());
                double prix = Double.parseDouble(tfprix.getText());
                int nbexp = Integer.parseInt(tfnbexemp.getText());
                int nbmot_int = (nbmot.isEmpty())?0 : Integer.parseInt(nbmot);
                if(OuvrageDAO.existeOuvrage(isbn)>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur 1");
                    alert.setHeaderText("Echec d'ajout de l'ouvrage!");
                    alert.setContentText("Un y'a un erreur lors de verification de l'etat de l'ouvrage, impossible d'ajouter l'ouvrage, essayez de nouveau!");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                }
                else {
                    int res = OuvrageDAO.saveOuvrage(titre, isbn, prix, bSeller, nbexp, auteur, genre, illust, date_p, lang, nbmot_int);
                    if (res == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Succès");
                        alert.setHeaderText("Ajout avec succès!");
                        alert.setContentText("L'ouvrage a été ajouté avec succès");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        numberAnimation--;
                        tftitre.clear();
                        tfisbn.clear();
                        tfprix.clear();
                        tfnbexemp.clear();
                        tfseller.clear();

                        switch(choix_type.getValue()){
                            case "Livre":
                                tfgenre.clear();
                                tfauteur.clear();
                                hbox_livre.setVisible(false);
                                break;
                            case "Magazine":
                                hbox_magazaine.setVisible(false);
                                break;
                            case "Bande déssinée":
                                tfillust.clear();
                                hbox_illustarteur.setVisible(false);
                                break;
                            case "Dictionnaire":
                                tflangue.clear();
                                tfnbmot.clear();
                                hbox_dict.setVisible(false);
                                break;
                            default:
                                break;
                        }
                        choix_type.setValue(null);
                        animatePageUp(b_ajout);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur 2");
                        alert.setHeaderText("Echec d'ajout de l'ouvrage!");
                        alert.setContentText("Un y'a un erreur inconnu, impossible d'ajouter l'ouvrage, essayez de nouveau!");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                    }
                }


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

    private void animatePageUp(Button b){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.millis(300));
        slide.setNode(b);

        slide.setByY(-70);
        slide.play();

    }

    private void showHiddenFields(ActionEvent e,Button b){
        if (choix_type.getValue()!=null) {
            if (numberAnimation==0) {
                animatePage(b);
                numberAnimation++;
            }
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



}
