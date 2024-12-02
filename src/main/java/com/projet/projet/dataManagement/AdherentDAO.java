package com.projet.projet.dataManagement;

import com.projet.projet.personne.adherent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {
    public static int saveAdherant(adherent adh){
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
            return 0;

        }
        catch (SQLException e){
            if(e.getErrorCode() == 1062){
                System.out.println("adh existe deja");
                return -1;
            }
            else{
                e.printStackTrace();
                return -2;
            }
        }

    }


    public static List<adherent> afficherAdherant(){
        List<adherent> Ladh = new ArrayList<>();
        String query = "select * from adherent";
        try(Connection conn = DataBaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()){
           while (rs.next()){
               adherent adh = new adherent(rs.getInt("cin"),
                       rs.getString("nom"),
                       rs.getString("prenom"),
                       rs.getInt("nbEmprunt")
               );
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



    public static int deleteAdherent(int cin){
        String query= "delete from adherent where cin = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,cin);
            int res = stmt.executeUpdate();
            if(res > 0){
                return 1; //il ya update
            }
            else return 0; //pas d'upadte => n'existe pas

        }catch(SQLException e){
            e.printStackTrace();
            return -1; //erreur

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

    public static void decrementNombreEmpruntAdh(int cin){
        String query= "update adherent set nbEmprunt = nbEmprunt -1 where cin = ?";
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


    public static int existeAdherent(int cin) {
        String query = "SELECT COUNT(*) as count FROM adherent WHERE cin = ? ";
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


    public static int UpdateAdherant(adherent adh){
        String query = "update adherent set nom =? , prenom = ? where cin = ?";
        try (
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,adh.getNom());
            stmt.setString(2,adh.getPrenom());
            stmt.setInt(3,adh.getCin());

            return stmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;

        }

    }





}
