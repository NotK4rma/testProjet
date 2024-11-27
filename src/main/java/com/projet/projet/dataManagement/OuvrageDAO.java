package com.projet.projet.dataManagement;

import com.projet.projet.ouvrage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OuvrageDAO {
    public static void saveOuvrage(ouvrage ouv){
        String query = "insert into adherent(isbn,titre,prix,bestSeller,nbExemplaire,auteur,genre,illustrateur,datePublication,langue,nbMot) values(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,ouv.getIsbn());
            stmt.setString(2,ouv.getTitle());
            stmt.setDouble(3,ouv.getPrix());
            stmt.setBoolean(4,ouv.getBestseller());
            stmt.setInt(5,ouv.getNbexemplaire());
            if(ouv instanceof livre){
                stmt.setString(6,((livre)ouv).getAuteur());
                stmt.setString(7,((livre)ouv).getGenre());
                System.out.println("livre");
            }
            else if(ouv instanceof bandeDessinee){
                stmt.setString(8,((bandeDessinee)ouv).getIllustrateur());
                System.out.println("bd");

            } else if (ouv instanceof magazine) {
                stmt.setDate(9,java.sql.Date.valueOf(((magazine)ouv).getDatePublication()));
                System.out.println("magazine");

            }
            else{
                stmt.setString(10,((dictionnaire)ouv).getLangue());
                stmt.setInt(11,((dictionnaire)ouv).getNombreMots());
                System.out.println("dictionnarie");

            }


            stmt.executeUpdate();
            System.out.println("ouvrage enregistre");

        }
        catch (SQLException e){
            e.printStackTrace();
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
            }


        }catch (SQLException e){
            e.printStackTrace();
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



    











}
