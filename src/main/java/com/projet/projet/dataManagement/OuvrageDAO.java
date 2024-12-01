package com.projet.projet.dataManagement;

import com.projet.projet.ouvrage.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OuvrageDAO {
    public static int saveOuvrage(String titre, String isbn, double prix, boolean seller, int nbexmplr, String auteur, String genre, String illust, LocalDate pub, String lang, int mot){
        String query = "insert into ouvrage(isbn,titre,prix,bestSeller,nbExemplaire,auteur,genre,illustrateur,datePublication,langue,nbMot) values(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,isbn);
            stmt.setString(2,titre);
            stmt.setDouble(3,prix);
            stmt.setBoolean(4,seller);
            stmt.setInt(5,nbexmplr);
            if(!(auteur.isBlank())){
                stmt.setString(6,auteur);
                stmt.setString(7,genre);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.DATE);
                stmt.setNull(10, Types.VARCHAR);
                stmt.setNull(11, Types.INTEGER);
                System.out.println("livre");
            }
            else if(!(illust.isBlank())){
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setString(8,illust);
                stmt.setNull(9, Types.DATE);
                stmt.setNull(10, Types.VARCHAR);
                stmt.setNull(11, Types.INTEGER);
                System.out.println("bd");

            } else if (pub!=null) {
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setDate(9,java.sql.Date.valueOf(pub));
                stmt.setNull(10, Types.VARCHAR);
                stmt.setNull(11, Types.INTEGER);
                System.out.println("magazine");

            }
            else{
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.DATE);
                stmt.setString(10,lang);
                stmt.setInt(11,mot);
                System.out.println("dictionnarie");

            }


            int rowsajt = stmt.executeUpdate();
            if(rowsajt>0){
                System.out.println("ouvrage enregistre");
                return 0;
            }
            else return -1;


        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }


    public static List<ouvrage> afficherOuvrage(){
        List<ouvrage> Louv = new ArrayList<>();
        String query = "select * from ouvrage";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                ouvrage ouv = new ouvrage(rs.getString("titre"),
                        rs.getString("isbn"),
                        rs.getDouble("prix"),
                        rs.getBoolean("bestSeller"),
                        rs.getInt("nbExemplaire")
                        );
                Louv.add(ouv);
                System.out.println("ajout +1");
            }


        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("oops");
        }
        return Louv;

    }


    public static List<livre> afficherLivre(){
        List<livre> Llivre = new ArrayList<>();
        String query = "select * from ouvrage where auteur IS NOT NULL";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                livre liv = new livre(rs.getString("titre"),
                        rs.getString("isbn"),
                        rs.getDouble("prix"),
                        rs.getBoolean("bestSeller"),
                        rs.getInt("nbExemplaire"),
                        rs.getString("auteur"),
                        rs.getString("genre")
                        );
                Llivre.add(liv);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return Llivre;

    }


    public static List<dictionnaire> afficherDictionnaire(){
        List<dictionnaire> Ldict = new ArrayList<>();
        String query = "select * from ouvrage where langue IS NOT NULL";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                dictionnaire dict = new dictionnaire(rs.getString("titre"),
                        rs.getString("isbn"),
                        rs.getDouble("prix"),
                        rs.getBoolean("bestSeller"),
                        rs.getInt("nbExemplaire"),
                        rs.getString("langue"),
                        rs.getInt("nbMot")
                        );
                Ldict.add(dict);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return Ldict;

    }


    public static List<magazine> afficherMagazine(){
        List<magazine> Lmag = new ArrayList<>();
        String query = "select * from ouvrage where datePublication IS NOT NULL";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                magazine mag = new magazine(rs.getString("titre"),
                        rs.getString("isbn"),
                        rs.getDouble("prix"),
                        rs.getBoolean("bestSeller"),
                        rs.getInt("nbExemplaire"),
                        (rs.getDate("datePublication")).toLocalDate()
                        );
                Lmag.add(mag);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return Lmag;

    }



    public static List<bandeDessinee> afficherBd(){
        List<bandeDessinee> Lbd = new ArrayList<>();
        String query = "select * from ouvrage where illustrateur IS NOT NULL";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                bandeDessinee bd = new bandeDessinee(rs.getString("titre"),
                        rs.getString("isbn"),
                        rs.getDouble("prix"),
                        rs.getBoolean("bestSeller"),
                        rs.getInt("nbExemplaire"),
                        rs.getString("illustrateur")
                        );
                Lbd.add(bd);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return Lbd;

    }

    public static List<ouvrage> chercherOuvrageISBN(String isbn){
        String query = "select * from ouvrage where isbn = ?";
        List<ouvrage> Louv = new ArrayList<>();
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1,isbn);
                try(ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        ouvrage ouv = new ouvrage(
                              rs.getString("titre"),
                              rs.getString("isbn"),
                              rs.getDouble("prix"),
                              rs.getBoolean("bestSeller"),
                              rs.getInt("nbExemplaire")
                        );
                        Louv.add(ouv);
                    }

                }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Louv;

    }


    public static int deleteOuvrage(String isbn){
        String query= "delete from ouvrage where isbn = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){
            stmt.setString(1,isbn);
            int res = stmt.executeUpdate();
            if(res > 0){
                return 1;
            }
            else return 0;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;

        }

    }


    public static int getNombreExemplaire(String isbn){
        String query= "select * from ouvrage  where isbn = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,isbn);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt("nbExemplaire");
                }

            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return -1;

    }


    public static void incrementNombreExemplaire(String isbn){
        String query= "update ouvrage set nbExemplaire = nbExemplaire +1 where isbn = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,isbn);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }

    }


    public static int existeOuvrage(String isbn) {
        String query = "SELECT COUNT(*) as count FROM ouvrage WHERE isbn = ? ";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }














}
