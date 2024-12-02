package com.projet.projet.dataManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiscStatsDAO {

    public static int getNbOuvrageDistinct(){
        String query = "select count(*) as count from ouvrage";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()){
                return rs.getInt("count");
            }

            return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }

    }




    public static int getNbAdherent(){
        String query = "select count(*) as count from adherent";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()){
                return rs.getInt("count");
            }

            return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }

    }




    public static int getNbBibliothecaire(){
        String query = "select count(*) as count from bibliothecaire";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()){
                return rs.getInt("count");
            }

            return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }

    }

    public static int getNbOuvrageTotal(){
        String query = "select sum(nbExemplaire) as sum from ouvrage";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()){
                return rs.getInt("sum");
            }

            return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }

    }


    public static int getNbEmprunt(){
        String query = "select count(*) as count from emprunt";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ){
            if (rs.next()){
                return rs.getInt("count");
            }

            return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }

    }





}
