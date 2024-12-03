/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.pret;

import com.projet.projet.dataManagement.EmpruntDAO;

import java.time.LocalDate;

public class prets {
    private int numPre;
    private int cinAdh;
    private String ISBNouv;
    private LocalDate dateEmp;
    private LocalDate dateRetour;
    private static int id=0;
    private static  int maxNbEmprunt = 5;
    private static  int maxjouremprunt = 14;


    public static int getMaxNbEmprunt(){
        return maxNbEmprunt;
    }


    public static int getMaxjouremprunt() {
        return maxjouremprunt;
    }

    public int getNumPre() {
        return numPre;
    }

    public static void setMaxjouremprunt(int maxjouremprunt) {
        prets.maxjouremprunt = maxjouremprunt;
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


    public int getCinAdh() {
        return cinAdh;
    }



    public void setCinAdh(int cinAdh) {
        this.cinAdh = cinAdh;
    }


    public String getISBNouv() {
        return ISBNouv;
    }



    public void setISBNouv(String ISBNouv) {
        this.ISBNouv = ISBNouv;
    }



    public void setDateEmp(LocalDate dateEmp) {
        this.dateEmp = dateEmp;
    }




    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }



    public prets(int cin, String isbn,LocalDate dateEmp, LocalDate dateRetour){
        this.cinAdh=cin;
        this.ISBNouv=isbn;
        this.dateEmp=dateEmp;
        this.dateRetour=dateRetour;
        this.numPre= EmpruntDAO.getIdPret(isbn,cin,dateEmp);

        
    }

    public prets(int cin, String isbn,LocalDate dateEmp){
        this.cinAdh=cin;
        this.ISBNouv=isbn;
        this.dateEmp=dateEmp;
        this.dateRetour=null;
        this.numPre= EmpruntDAO.getIdPret(isbn,cin,dateEmp);


    }





    //private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");


    /*public void afficherDetailsPret (){
        if (this.dateRetour==null){
            System.out.println("L'indentifiant est: "+ numPre +" L'adherent est: "+ adh.toString()+" le livre est : "+ouv.toString()+" date de l'emprunt : "+this.dateEmp.format(formatter)+" date de retour indisponible " );
        }
        else{
            System.out.println("L'indentifiant est: "+ numPre +" L'adherent est: "+ adh.toString()+" le livre est : "+ouv.toString()+" date de l'emprunt : "+this.dateEmp.format(formatter)+" date de retour : "+this.dateRetour.format(formatter) );
        }
        
    }*/
    
    
}
