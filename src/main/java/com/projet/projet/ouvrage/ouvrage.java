/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.ouvrage;

/**
 *
 * @author hp
 */
public class ouvrage {
        private String title;
        private String isbn;
        private double prix;
        private boolean bestseller;
        private int nbexemplaire;

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrix() {
        return prix;
    }

    public boolean getBestseller() {
        return bestseller;
    }

    public int getNbexemplaire() {
        return nbexemplaire;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setBestseller(boolean bestseller) {
        this.bestseller = bestseller;
    }

    public void setNbexemplaire(int nbexemplaire) {
        this.nbexemplaire = nbexemplaire;
    }
        
    public ouvrage(String title, String isbn, double prix, boolean bestseller, int nbexemplaire){
        this.title=title;
        this.isbn=isbn;
        this.prix=prix;
        this.bestseller=bestseller;
        this.nbexemplaire=nbexemplaire;
    }
    
    public ouvrage(){}
        public String toString(){
            return "Titre : "+this.title+" isbn : "+this.isbn+" prix : "+this.prix+" Best seller ? "+this.bestseller+" nombre exemplaire : "+this.nbexemplaire ;  
        }
}
