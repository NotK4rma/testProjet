package com.projet.projet.controllers;

import com.projet.projet.dataManagement.AdherentDAO;
import com.projet.projet.dataManagement.EmpruntDAO;
import com.projet.projet.dataManagement.OuvrageDAO;
import com.projet.projet.pret.prets;
import com.projet.projet.utilsScene.SceneMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterEmpruntController implements Initializable {

    @FXML
    private Button b_ajout;

    @FXML
    private DatePicker dateemp;

    @FXML
    private DatePicker dateretour;


    @FXML
    private TextField tfcin;

    @FXML
    private TextField tfisbn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_ajout.setOnMouseClicked(e->ajouterEmprunt());


    }


    private void ajouterEmprunt(){
        String cin = tfcin.getText();
        String isbn = tfisbn.getText();
        LocalDate emp = dateemp.getValue();
        LocalDate retour = dateretour.getValue();
        if(cin.isBlank() || !(SceneMethods.isInteger(cin)) || isbn.isBlank() || emp==null || !(SceneMethods.isInteger(isbn))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vérifier les champs de texte!");
            alert.setContentText("Prière de remplir tous les champs disponibles.");
            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
            alert.showAndWait();

        }
        else{
            if(retour!=null && retour.isBefore(emp)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vérifier les champs de Date!");
                alert.setContentText("Date de retour entré est avant la date d'emprunt!");
                Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                alert.showAndWait();
                dateemp.setValue(null);
                dateretour.setValue(null);
            }
            else{
                if (AdherentDAO.existeAdherent(Integer.parseInt(cin))>0 && OuvrageDAO.existeOuvrage(isbn)>0) {
                    if (EmpruntDAO.existeEmrpunt(Integer.parseInt(cin),isbn,emp)==0) {
                        if (OuvrageDAO.getNombreExemplaire(isbn)>0 && AdherentDAO.getNombreEmpruntAdh(Integer.parseInt(cin))< prets.getMaxNbEmprunt()) {
                            prets pret = new prets(Integer.parseInt(cin),isbn,emp,retour);
                            int res = EmpruntDAO.saveEmprunt(pret);
                            switch (res){
                                case -1:
                                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                                    alert2.setTitle("Erreur ");
                                    alert2.setHeaderText("Echec d'ajout de l'emprunt!");
                                    alert2.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, cet emprunt existe deja, essayez de nouveau!");
                                    Stage alertStage2 = (Stage)alert2.getDialogPane().getScene().getWindow();
                                    alertStage2.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                                    alert2.showAndWait();
                                    dateretour.setValue(null);
                                    dateemp.setValue(null);
                                    break;
                                case 0:
                                case -2:
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Erreur ");
                                    alert.setHeaderText("Echec d'ajout de l'emprunt!");
                                    alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, essayez de nouveau!");
                                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                                    alert.showAndWait();
                                    dateretour.setValue(null);
                                    dateemp.setValue(null);
                                    tfisbn.clear();
                                    tfcin.clear();
                                    break;
                                case 1:
                                    if (retour==null) {
                                        AdherentDAO.incrementNombreEmpruntAdh(Integer.parseInt(cin));
                                        OuvrageDAO.decrementNombreExemplaire(isbn);
                                    }
                                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                                    alert3.setTitle("Succès");
                                    alert3.setHeaderText("Ajout réussie!");
                                    alert3.setContentText("L'emprunt a été ajouté avec succès");
                                    Stage alertStage3 = (Stage)alert3.getDialogPane().getScene().getWindow();
                                    alertStage3.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                                    alert3.showAndWait();
                                    tfcin.clear();
                                    tfisbn.clear();
                                    dateemp.setValue(null);
                                    dateretour.setValue(null);
                                    break;
                                default:
                                    break;


                            }
                        } else if (OuvrageDAO.getNombreExemplaire(isbn)==0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur ");
                            alert.setHeaderText("Echec d'ajout de l'emprunt!");
                            alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt,Il n'existe plus d'exemplaire pour ce livre!");
                            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                            alert.showAndWait();
                            dateretour.setValue(null);
                            dateemp.setValue(null);
                            tfcin.clear();
                            tfisbn.clear();

                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur ");
                            alert.setHeaderText("Echec d'ajout de l'emprunt!");
                            alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt,Cet adhérent a le nombre maximum d'emprunt deja!Il doit restituer au moin un livre!");
                            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                            alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                            alert.showAndWait();
                            dateretour.setValue(null);
                            dateemp.setValue(null);
                            tfcin.clear();
                            tfisbn.clear();
                        }
                    }else if(EmpruntDAO.existeEmrpunt(Integer.parseInt(cin),isbn,emp)>0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur ");
                        alert.setHeaderText("Echec d'ajout de l'emprunt!");
                        alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt,Il existe deja un emprunt ayant le meme CIN, ISBN et date d'emprunt!");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        dateretour.setValue(null);
                        dateemp.setValue(null);
                        tfcin.clear();
                        tfisbn.clear();

                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur ");
                        alert.setHeaderText("Echec d'ajout de l'emprunt!");
                        alert.setContentText("Un y'a une erreur inconnu lors de l'ajout de l'emprunt!\nEssayez de nouveau!");
                        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                        alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                        alert.showAndWait();
                        dateretour.setValue(null);
                        dateemp.setValue(null);
                        tfcin.clear();
                        tfisbn.clear();

                    }


                }
                else if (AdherentDAO.existeAdherent(Integer.parseInt(cin))>0 && OuvrageDAO.existeOuvrage(isbn)<=0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur ");
                    alert.setHeaderText("Echec d'ajout de l'emprunt!");
                    alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, Cet ISBN n'existe pas!\n Ajouter l'ouvrage puis essayez de nouveau!");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    dateretour.setValue(null);
                    dateemp.setValue(null);
                    tfisbn.clear();
                } else if (AdherentDAO.existeAdherent(Integer.parseInt(cin))<=0 && OuvrageDAO.existeOuvrage(isbn)>0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur ");
                    alert.setHeaderText("Echec d'ajout de l'emprunt!");
                    alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, Ce CIN n'existe pas!\n Ajouter l'adhérent puis essayez de nouveau!");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    dateretour.setValue(null);
                    dateemp.setValue(null);
                    tfcin.clear();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur ");
                    alert.setHeaderText("Echec d'ajout de l'emprunt!");
                    alert.setContentText("Un y'a une erreur lors de l'ajout de l'emprunt, Ce CIN et ISBN n'existent pas!\n Ajouter l'adhérent et l'ouvrage puis essayez de nouveau!");
                    Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("com/projet/projet/Images/logo.png"));
                    alert.showAndWait();
                    dateretour.setValue(null);
                    dateemp.setValue(null);
                    tfcin.clear();
                    tfisbn.clear();

                }


            }


        }


    }



}
