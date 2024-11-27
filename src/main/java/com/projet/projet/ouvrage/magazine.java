/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projet.projet.ouvrage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author hp
 */
public class magazine extends ouvrage {
    private LocalDate datePublication;
    
    
    public magazine(String title, String isbn, double prix, boolean bestseller, int nbexemplaire, LocalDate datePublication){
        super(title,isbn,prix,bestseller,nbexemplaire);
        this.datePublication=datePublication;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
    
    public String toString(){
            return super.toString() + "Date publication : "+ this.datePublication.format(formatter);  
    }  
}
