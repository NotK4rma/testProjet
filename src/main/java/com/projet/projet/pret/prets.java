/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.pret;
import com.projet.projet.adherant.adherent;
import com.projet.projet.ouvrage.livre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author hp
 */
public class prets {
    private int numPre;
    private adherent adh;
    private livre liv;
    private LocalDate dateEmp;
    private LocalDate dateRetour;
    private static int id=0;

    public int getNumPre() {
        return numPre;
    }

    public adherent getAdh() {
        return adh;
    }

    public livre getLivre() {
        return liv;
    }

    public LocalDate getDateEmp() {
        return dateEmp;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setNumPre(int numPre) {
        this.numPre = numPre;
    }

    public void setAdh(adherent adh) {
        this.adh = adh;
    }

    public void setLivre(livre livre) {
        this.liv = livre;
    }

    public void setDateEmp(LocalDate dateEmp) {
        this.dateEmp = dateEmp;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }
    public prets(adherent adh, livre liv,LocalDate dateEmp, LocalDate dateRetour){
        this.numPre=id++;
        this.adh=adh;
        this.liv=liv;
        this.dateEmp=dateEmp;
        this.dateRetour=dateRetour;
        
    }
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
    
    public void afficherDetailsPret (){
        if (this.dateRetour==null){
            System.out.println("L'indentifiant est: "+ numPre +" L'adherent est: "+ adh.toString()+" le livre est : "+liv.toString()+" date de l'emprunt : "+this.dateEmp.format(formatter)+" date de retour indisponible " );
        }
        else{
            System.out.println("L'indentifiant est: "+ numPre +" L'adherent est: "+ adh.toString()+" le livre est : "+liv.toString()+" date de l'emprunt : "+this.dateEmp.format(formatter)+" date de retour : "+this.dateRetour.format(formatter) );
        }
        
    }
    
    
}
