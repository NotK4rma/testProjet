/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.bibliothéque;
import com.projet.projet.adherant.adherent;
import com.projet.projet.ouvrage.livre;
import com.projet.projet.ouvrage.ouvrage;
import com.projet.projet.pret.prets;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Bibliotheque {
    private String name;
    private String adresse;
    private  ArrayList<adherent> Ladh;
    private  ArrayList<ouvrage> Louv;
    private  ArrayList<prets> Lpret;
    
    public Bibliotheque(String name, String adresse){
        this.name=name;
        this.adresse=adresse;   
        this.Ladh= new ArrayList<>();
        this.Louv= new ArrayList<>();
        this.Lpret= new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public adherent rechercheAdhCin(int cin){
        for(adherent adh : Ladh){
            if(adh.getCin()==cin){
                return adh;
            }
            
        }
        return null;
    }
    
    
    public void InscrireAdh(adherent adh){
        Ladh.add(adh);
    }
    
    void desincsrireAdhCinh(int cin){
        adherent adh=rechercheAdhCin(cin);
        if(adh==null){
            System.out.println("N'existe pas");
        }
        else{
            if(adh.getNbemprunt()!=0){
                System.out.println("Adherent a un emprunt en cours on ne peut pas le relever.");
            }
            else{
                Ladh.remove(adh);
            }
        }
    }
    
    public void afficherAdh(){
        for(adherent adh : Ladh){
            System.out.println(adh.toString());
        }
    }
    
    public ouvrage rechercherOuvrageIsbn(String isbn){
        for(ouvrage ouv :Louv){
            if(ouv.getIsbn().equalsIgnoreCase(isbn)){
                return ouv;
            }
        }
        return null;
    }
    
    public void ajouterOuvr(ouvrage ouv){
        Louv.add(ouv);
    }
    
    public void supprimerOuvrIsbn(String isbn){
    ouvrage ouv=rechercherOuvrageIsbn(isbn);
    if(ouv==null){
        System.out.println("N'existe pas");
    }
    else{
        boolean existe=false;
        for(prets emp :Lpret){
            if(emp.getLivre().getIsbn().equalsIgnoreCase(isbn)){
                existe=true;
                break;
            }
        }
        if(existe){
            System.out.println("Le livre existe et est en cours d'utilisation");
        }
        else{
            Louv.remove(ouv);
        }
        } 
    }
    
    public void afficherOuvrage(){
        for(ouvrage ouv :Louv){
            System.out.println(ouv.toString());
        }
    }
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
    
    public ArrayList<prets> chercherPretParDate(LocalDate date){
        ArrayList<prets> temp= new ArrayList<>();
        for(prets emp : Lpret){
            if(emp.getDateEmp().format(formatter).equals(date.format(formatter))){
                 temp.add(emp);
            }
        }
        return temp;
    }
    
    public ArrayList<prets> chercherPretCin(int cin){
    ArrayList<prets> temp= new ArrayList<>();
    for(prets emp : Lpret){
        if(emp.getAdh().getCin()==cin){
            temp.add(emp);
        }
    }
        return temp;
    }
    
    
    void enregistrerPret(String isbn, int cin){
        for(prets emp: Lpret){
            if(emp.getAdh().getCin()==cin && emp.getLivre().getIsbn().equals(isbn)){
                System.out.println("Le livre deja existe ");
            }    
        }
            
                adherent adh= rechercheAdhCin(cin);
                ouvrage ouv = rechercherOuvrageIsbn(isbn);
                if (!(ouv instanceof livre)){
                    System.out.println("Seul les livre peuvent etre emprunte");
                }
                else{
                    livre liv = (livre) ouv;
                
                if(liv.getNbexemplaire()==0 || adh.getNbemprunt()==3){
                    System.out.println("Impossible");
                }
                else{
                    prets temp = new prets(adh,liv,LocalDate.now(),null);
                    Lpret.add(temp);
                    adh.setNbemprunt(adh.getNbemprunt()+1);
                    liv.setNbexemplaire(liv.getNbexemplaire()-1);
                    System.out.println("Succes");
                }
            }
        }
    
    public void restituerPret(int cin){
        ArrayList<prets> empAdh=chercherPretCin(cin);
        if (empAdh==null){
            System.out.println("N'existe pas");
        }
        else{
            int i=0;
            for(prets elt: empAdh){
                System.out.print((++i));
                elt.afficherDetailsPret();   
            }
             Scanner scan = new Scanner(System.in);
             System.out.println("Donner le num que vous voulez supprimer: ");
             int decision= scan.nextInt();
             scan.next();
             livre liv= empAdh.get(decision-1).getLivre();
             liv.setNbexemplaire(liv.getNbexemplaire()+1);
             adherent adh=rechercheAdhCin(cin);
             adh.setNbemprunt(adh.getNbemprunt()-1);
             Lpret.remove(empAdh.get(decision-1));
             empAdh.clear();
             scan.close();  
        }
    }

    public void afficherPret(){
        for(prets elt: Lpret){
            elt.afficherDetailsPret ();
        }
    }


    
    
   
                                                    
    

    
   
    
    
    
}
