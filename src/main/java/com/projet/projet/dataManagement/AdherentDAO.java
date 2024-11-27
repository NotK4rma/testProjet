package com.projet.projet.dataManagement;

import com.projet.projet.adherant.adherent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {
    public static void saveAdherant(int c, String n, String p){
        String query = "insert into adherent(cin,nom,prenom) values(?,?,?)";
        try (
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,c);
            stmt.setString(2,n);
            stmt.setString(3,p);

            stmt.executeUpdate();
            System.out.println("adherent enregistre");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


    public static List<adherent> afficherAdherant(){
        List<adherent> Ladh = new ArrayList<>();
        String query = "select * from adherent";
        try(Connection conn = DataBaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()){
           while (rs.next()){
               adherent adh = new adherent(rs.getInt("cin"), rs.getString("nom"), rs.getString("prenom"));
               Ladh.add(adh);
           }


        }
        catch (SQLException e){
            e.printStackTrace();
        }


        return Ladh;
    }



    public static List<adherent> rechercheAdhCin(int c){
        List <adherent> Ladh = new ArrayList<>();
        String query = "select * from adherent where cin = ?";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1,c);
                try(ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        adherent adh = new adherent(rs.getInt("cin"),
                                rs.getString("nom"),
                                rs.getString("prenom")
                                );
                        Ladh.add(adh);
                    }
                }


        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Ladh;


    }



}
