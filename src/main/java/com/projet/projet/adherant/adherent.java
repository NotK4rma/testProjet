/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.adherant;

/**
 *
 * @author hp
 */
public class adherent {
    private int cin;
    private String nom;
    private String prenom;
    private int nbemprunt=0;

    
    public adherent(int cin,String nom,String prenom){
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;        
    }

    public adherent(int cin ,String nom, String prenom, int nbemprunt){
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.nbemprunt=nbemprunt;
    }


    public int getCin() {
        return cin;
    }

    public String getNom() {

        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNbemprunt() {
        return nbemprunt;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNbemprunt(int nbemprunt) {
        this.nbemprunt = nbemprunt;
    }
    
    
    public String toString(){
        return "cin :  "+ this.cin + " nom et prenom : "+ this.nom +" "+ this.prenom+ " nombre emprunts : "+ this.nbemprunt;
    }
}
