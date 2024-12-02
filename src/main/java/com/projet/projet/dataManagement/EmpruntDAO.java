package com.projet.projet.dataManagement;

import com.projet.projet.ouvrage.ouvrage;
import com.projet.projet.pret.prets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    public static int saveEmprunt(prets emp){
        String query = "insert into emprunt (idAdherant,idOuvrage,dateEmprunt,dateRetour) values(?,?,?,?)";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
                stmt.setInt(1,emp.getCinAdh());
                stmt.setString(2,emp.getISBNouv());
                stmt.setDate(3,java.sql.Date.valueOf(emp.getDateEmp()));
            if (emp.getDateRetour()!=null) {
                stmt.setDate(4, Date.valueOf(emp.getDateRetour()));
            }else{
                stmt.setNull(4,Types.DATE);
            }

            return stmt.executeUpdate();


        }catch (SQLException e){
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


    public static List<prets> afficherEmprunts(boolean courr){
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


    public static List<prets> afficherEmpruntsByISBN(boolean courr, String isbn){
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








    public static List<prets> afficherEmpruntsByCIN(boolean courr, int cin){
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




    public static List<prets> afficherEmpruntsByDateEmprunt(boolean courr, LocalDate dateEmp){
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


   /* public static int updateRestituerEmprunt(int cin, String isbn, LocalDate dateRetour){
        LocalDate d_emp = getDateEmprunt(cin,isbn);
        if(d_emp == null){
            System.out.println("date d'emprunt est null");
            return -1;
        } else if (dateRetour.isBefore(d_emp)) {
            System.out.println("date de retour avant date d'emprunt");
            return -2;
        }
        else {

            String query = "update emprunt set dateRetour = ? where idAdherant = ? and idOuvrage = ?";
            try (
                    Connection conn = DataBaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setDate(1, java.sql.Date.valueOf(dateRetour));
                stmt.setInt(2, cin);
                stmt.setString(3, isbn);

                int rowsUpadted = stmt.executeUpdate();
                if(rowsUpadted>0){
                    System.out.println("succes");
                }
                else {
                    System.out.println("isbn ou cin n'existe pas");
                    return -3;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }*/





    public static int existeEmrpunt(int cin, String isbn,LocalDate emp){
        String query = "select count(*) as count from emprunt where idAdherant = ? and idOuvrage = ? and dateEmprunt= ?";

        try(Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,cin);
            stmt.setString(2,isbn);
            stmt.setDate(3,java.sql.Date.valueOf(emp));
            try(ResultSet rs =stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("count");
                }
                return 0;

            }

        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

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


    public static LocalDate getDateEmprunt(int cin, String isbn){

        String query= "select * from emprunt where cin = ? and isbn = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setInt(1,cin);
            stmt.setString(2,isbn);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return (rs.getDate("dateEmprunt")).toLocalDate();
                }

            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;

    }


    public static int updateEmprunt(prets emp){

        String query = "update emprunt set dateRetour =? where idAdherant = ? and idOuvrage = ? and dateEmprunt = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setDate(1, Date.valueOf(emp.getDateRetour()));
            stmt.setInt(2,emp.getCinAdh());
            stmt.setString(3,emp.getISBNouv());
            stmt.setDate(4,java.sql.Date.valueOf(emp.getDateEmp()));




            return stmt.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }

    public static int getIdPret(String isbn, int cin , LocalDate d){
        String query = "select * from emprunt where idAdherant = ? and idOuvrage = ? and dateEmprunt= ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,cin);
            stmt.setString(2,isbn);
            stmt.setDate(3,java.sql.Date.valueOf(d));
            try(ResultSet rs =stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("idEmprunt");
                }
                return 0;

            }

        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }













}
