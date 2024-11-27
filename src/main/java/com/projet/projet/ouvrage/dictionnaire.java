/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.ouvrage;

/**
 *
 * @author hp
 */
public class dictionnaire extends volume {
    private String langue;
    private int nombreMots;
    public dictionnaire(String title, String isbn, double prix, boolean bestseller, int nbexemplaire,String langue, int nombreMots){
        super(title,isbn,prix,bestseller,nbexemplaire);
        this.langue=langue;
        this.nombreMots=nombreMots;
    }

    public String getLangue() {
        return langue;
    }

    public int getNombreMots() {
        return nombreMots;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setNombreMots(int nombreMots) {
        this.nombreMots = nombreMots;
    }
    
    public String toString(){
            return super.toString() + " langue : "+ this.langue + " nombre de mots : "+ this.nombreMots;  
    }  
    
}
