package org.smartcity.smartcity;

import java.sql.*;


/*
* Classe che gestisce le interazioni con il DB sqlite (src/main/resources/DB/SmartCityDb.sqlite)
* Driver: src/main/resources/DB/sqlite-jdbc-3.40.0.0.jar
* DDL: src/main/resources/DB/DDL.sql
*
* */

public class DbManager {

    private Connection conn;

    private boolean connect() throws SQLException {
        String path = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\DB\\SmartCityDb.sqlite"; //Database file Path
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

            System.out.println(connect() ? "Connected" : "Not Connected"); //Apro la connessione

            Statement stm = conn.createStatement(); //Defisco uno stetement
            ResultSet res = stm.executeQuery(SQL); //Eseguo lo statement

            conn.close(); //Chiudo la connessione

            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }


    }

}
