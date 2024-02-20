package org.smartcity.smartcity;

import java.sql.*;


/*
 * Classe che gestisce le interazioni con il DB sqlite (src/main/resources/DB/SmartCityDb.sqlite)
 * Driver: src/main/resources/DB/sqlite-jdbc-3.40.0.0.jar
 * DDL: src/main/resources/DB/DDL.sql
 *
 * */

public class DbManager {

    private Connection connect() throws SQLException {
        String path = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\DB\\SmartCityDb.sqlite"; //Database file Path, specificando i driver
        try {

            Connection conn = DriverManager.getConnection(path); //Connect
            conn.setAutoCommit(true);
            return conn; //Connection Done

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null; //Connection failed
        }


    }

    public ResultSet queryExec(String SQL) throws SQLException {
        try {

            Connection conn = connect();

            if (conn == null) {
                System.out.println("Impossibile Connettersi al DB");
            }

            assert conn != null;
            Statement stm = conn.createStatement(); //Defisco uno stetement
            ResultSet res = stm.executeQuery(SQL);

            //stm.close();
            //conn.close();

            return res;

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }


    }

    public void insertSensor(String nome, String posizione) throws SQLException {
        try {

            Connection conn = connect();
            if (conn == null) {
                System.out.println("Impossibile Connettersi al DB");
            }
            PreparedStatement ps = conn.prepareStatement("Insert into sensore (nome,locazione) values (?,?);"); //Preparo lo statement

            ps.setString(1, nome);
            ps.setString(2, posizione);
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }
    }

}
