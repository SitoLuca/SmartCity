package org.smartcity.smartcity;
import java.sql.*;


public class DbManager {

    private Connection conn;

    public boolean connect() throws SQLException {
        String path = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\DB\\SmartCityDb.sqlite"; //Database file Path
        System.out.println(path);
        try {

            conn = DriverManager.getConnection(path); //Connect
            conn.setAutoCommit(true); //Enable Auto Commit
            return true; //Connection Done

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; //Connection failed
        }


    }

    public ResultSet queryExec(String SQL) throws SQLException {
        try {

            Statement stm = conn.createStatement(); //Defina a stetement
            return  stm.executeQuery(SQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Show error message
            throw e;
        }


    }

}
