/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.ouvrage;

/**
 *
 * @author hp
 */
public class bandeDessinee extends volume{
    private String illustrateur;
    public bandeDessinee(String title, String isbn, double prix, boolean bestseller, int nbexemplaire,String illustrateur){
        super(title,isbn,prix,bestseller,nbexemplaire);
        this.illustrateur=illustrateur;
        
    }

    public String getIllustrateur() {
        return illustrateur;
    }

    public void setIllustrateur(String illustrateur) {
        this.illustrateur = illustrateur;
    }
    
    public String toString(){
            return super.toString() + "illustrateur : "+ this.illustrateur;  
    }  
}
