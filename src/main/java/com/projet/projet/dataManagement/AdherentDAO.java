package com.projet.projet.dataManagement;

import com.projet.projet.adherant.adherent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {
    public static void saveAdherant(adherent adh){
        String query = "insert into adherent(cin,nom,prenom) values(?,?,?)";
        try (
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,adh.getCin());
            stmt.setString(2,adh.getNom());
            stmt.setString(3,adh.getPrenom());

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



    public static void deleteAdherent(int cin){
        String query= "delete from adherent where cin = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,cin);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }

    }

    public static void incrementNombreEmpruntAdh(int cin){
        String query= "update adherent set nbEmprunt = nbEmprunt +1 where cin = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,cin);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }

    }


    public static int getNombreEmpruntAdh(int cin){
        String query= "select * from adherent  where cin = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,cin);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt("nbEmprunt");
                }

            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return -1;

    }


    public int existeAdherent(int cin) {
        String query = "SELECT COUNT(*) as count FROM aherent WHERE cin = ? ";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count"); // Return the count of matching rows
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }





}
