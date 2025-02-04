package org.smartcity.smartcity.dbProxy;

import java.sql.*;
import java.util.*;


/**
 * Classe che gestisce le interazioni con il DB sqlite (src/main/resources/DB/SmartCityDb.sqlite)
 * Driver: src/main/resources/DB/sqlite-jdbc-3.40.0.0.jar
 * DDL: src/main/resources/DB/DDL.sql
 */

public class ConcreteDbManager implements DbManager {

    Connection conn;

    ConcreteDbManager(Connection con) {
        conn = con;
    }

    @Override
    public final List<Map<String, Object>> queryExec(String query) throws SQLException {
        try {

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

            return queryRes;

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //Mostra errore
            throw e;
        }


    }

    @Override
    public void insert(String SQL) throws SQLException {

        assert conn != null;

        Statement ps = conn.createStatement(); //Preparo lo statement
        ps.executeUpdate(SQL);


    }


}
