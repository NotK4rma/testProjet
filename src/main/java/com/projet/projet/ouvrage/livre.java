/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.ouvrage;

/**
 *
 * @author hp
 */
public class livre extends volume{
    
    private String auteur;
    private String genre;
    public livre(String title, String isbn, double prix, boolean bestseller, int nbexemplaire,String auteur, String genre){
        super(title,isbn,prix,bestseller,nbexemplaire);
        this.auteur=auteur;
        this.genre=genre;

    }

    public String getAuteur() {
        return auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
        public String toString(){
            return super.toString()+ "Auteur : "+this.auteur+ " genre : "+ this.genre;   
        }    
}