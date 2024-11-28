package com.projet.projet.dataManagement;

import com.projet.projet.ouvrage.ouvrage;
import com.projet.projet.pret.prets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    public void saveEmprunt(prets emp){
        String query = "insert into emprunt (idAdherant,idOuvrage,dateEmprunt,dateRetour) values(?,?,?,?)";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
                stmt.setInt(1,emp.getCinAdh());
                stmt.setString(2,emp.getISBNouv());
                stmt.setDate(3,java.sql.Date.valueOf(emp.getDateEmp()));
                stmt.setDate(4,java.sql.Date.valueOf(emp.getDateRetour()));

                stmt.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }




    }


    public List<prets> afficherEmprunts(boolean courr){
        String query = "select * from emprunt";
        List<prets> Lemp = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                if(!courr){
                    if(rs.getDate("dateRetour")==null){
                        prets emp = new prets(
                                rs.getInt("idAdherant"),
                                rs.getString("idOuvrage"),
                                (rs.getDate("dateEmprunt")).toLocalDate()

                        );
                        Lemp.add(emp);

                    }
                    else {
                        prets emp = new prets(
                                rs.getInt("idAdherant"),
                                rs.getString("idOuvrage"),
                                (rs.getDate("dateEmprunt")).toLocalDate(),
                                (rs.getDate("dateRetour")).toLocalDate()
                        );
                        Lemp.add(emp);
                    }


                }
                else {
                    if(rs.getDate("dateRetour")==null) {
                        prets emp = new prets(
                                rs.getInt("idAdherant"),
                                rs.getString("idOuvrage"),
                                (rs.getDate("dateEmprunt")).toLocalDate()
                        );
                        Lemp.add(emp);
                    }

                }



            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        return Lemp;

    }


    public List<prets> afficherEmpruntsByISBN(boolean courr, String isbn){
        String query = "select * from emprunt where idOuvrage = ?";
        List<prets> Lemp = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1,isbn);
            try(ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    if (!courr) {
                        if (rs.getDate("dateRetour") == null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()

                            );
                            Lemp.add(emp);

                        } else {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate(),
                                    (rs.getDate("dateRetour")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }


                    } else {
                        if(rs.getDate("dateRetour")==null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }

                    }


                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Lemp;

    }








    public List<prets> afficherEmpruntsByCIN(boolean courr, int cin){
        String query = "select * from emprunt where idAdherant = ?";
        List<prets> Lemp = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setInt(1,cin);
            try(ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    if (!courr) {
                        if (rs.getDate("dateRetour") == null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()

                            );
                            Lemp.add(emp);

                        } else {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate(),
                                    (rs.getDate("dateRetour")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }


                    } else {
                        if(rs.getDate("dateRetour")==null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }

                    }


                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Lemp;

    }




    public List<prets> afficherEmpruntsByDateEmprunt(boolean courr, LocalDate dateEmp){
        String query = "select * from emprunt where dateEmprunt = ?";
        List<prets> Lemp = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setDate(1,java.sql.Date.valueOf(dateEmp));
            try(ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    if (!courr) {
                        if (rs.getDate("dateRetour") == null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()

                            );
                            Lemp.add(emp);

                        } else {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate(),
                                    (rs.getDate("dateRetour")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }


                    } else {
                        if(rs.getDate("dateRetour")==null) {
                            prets emp = new prets(
                                    rs.getInt("idAdherant"),
                                    rs.getString("idOuvrage"),
                                    (rs.getDate("dateEmprunt")).toLocalDate()
                            );
                            Lemp.add(emp);
                        }

                    }


                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Lemp;

    }


    public void updateEmprunt(int cin, String ISBN){
        String query = "update emprunt set ";


    }

    public int existeEmrpunt(int cin, String isbn){
        String query = "select * from emprunt where idAdherant = ? and idOuvrage = ?";
        int nbColumn = 0;
        try(Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,cin);
            stmt.setString(2,isbn);
            try(ResultSet rs =stmt.executeQuery()){
                while(rs.next()){
                    nbColumn++;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return nbColumn;

    }

    /*

    another example of existe(count)emprunt
    public int countEmprunt(int cin, String isbn) {
    String query = "SELECT COUNT(*) AS count FROM emprunt WHERE idAdherant = ? AND idOuvrage = ?";
    try (Connection conn = DataBaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, cin);
        stmt.setString(2, isbn);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count"); // Return the count of matching rows
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // No matches found
}
*/














}
