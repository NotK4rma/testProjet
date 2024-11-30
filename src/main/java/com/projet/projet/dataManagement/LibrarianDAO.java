package com.projet.projet.dataManagement;

import com.projet.projet.adherant.adherent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAO {

    public static int saveLibrarian(String un, String pw){
        String query = "insert into bibliothecaire(username,password) values (?,?)";
        try(
                Connection conn =DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){

            stmt.setString(1,un);
            stmt.setString(2,pw);

            stmt.executeUpdate();
            return 0;

        }
        catch (SQLException e){
            if(e.getErrorCode() == 1062){
                System.out.println("libr existe deja");
                return -1;
            }
            else{
                e.printStackTrace();
                return -2;
            }
        }

    }


    public static boolean getLibr(String un, String pw){
        String query = "select count(*) as count from bibliothecaire where username = ? and BINARY password = ?";
        int res=0;
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,un);
            stmt.setString(2,pw);
           try( ResultSet rs = stmt.executeQuery()){
               if (rs.next()){
                  res = rs.getInt("count");
               }
               return res==1;
           }


        }catch(SQLException e){
            e.printStackTrace();;
            return false;
        }


    }


    public static int deleteLibr(String un){
        String query = "delete from bibliothecaire where username = ?";
        try(
                Connection conn = DataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ){
            stmt.setString(1,un);

            stmt.executeUpdate();
            return stmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }



    public static List<String> afficherLibrs(){
        List<String> Llibr = new ArrayList<>();
        String query = "select * from bibliothecaire";
        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Llibr.add(rs.getString("username"));
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }


        return Llibr;

    }


}
