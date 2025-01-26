package org.smartcity.smartcity;

import java.sql.*;
import java.util.*;


/**
 * Classe che gestisce le interazioni con il DB sqlite (src/main/resources/DB/SmartCityDb.sqlite)
 * Driver: src/main/resources/DB/sqlite-jdbc-3.40.0.0.jar
 * DDL: src/main/resources/DB/DDL.sql
 */

public class DbManager {

    private static DbManager instance = null;

    private DbManager() {
    }

    public static DbManager getInstance() {
        /*
        Semplificazione di
        if (instance == null) {return new DbManager; } else { return instance; }
        */
        return Objects.requireNonNullElseGet(instance, DbManager::new);
    }

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


    public final List<Map<String, Object>> queryExec(String query) throws SQLException {
        try {

            Connection conn = connect();

            assert conn != null;

            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);

            ResultSetMetaData metaData = res.getMetaData(); //Recupero metadati
            int columnCount = metaData.getColumnCount();  //Recupero "Numero colonne dai metadati"

            List<Map<String, Object>> queryRes = new ArrayList<Map<String, Object>>();

            while (res.next()) {
                Map<String, Object> row = new HashMap<String, Object>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = res.getObject(columnName);
                    row.put(columnName, value);
                }

                queryRes.add(row);

            }

            stm.close();
            conn.close();

            return queryRes;

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }


    }

    public void insert(String SQL) throws SQLException {
        try {

            Connection conn = connect();

            assert conn != null;
            Statement ps = conn.createStatement(); //Preparo lo statement

            ps.executeUpdate(SQL);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }
    }


}
