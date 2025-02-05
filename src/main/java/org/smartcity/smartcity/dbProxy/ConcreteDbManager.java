package org.smartcity.smartcity.dbProxy;

import java.sql.*;
import java.util.*;

/**
 * Classe che gestisce le interazioni con il database SQLite utilizzato dalla Smart City.
 *
 * La classe si occupa di eseguire query e inserimenti nel database SQLite situato in
 * {@code src/main/resources/DB/SmartCityDb.sqlite} utilizzando il driver JDBC {@code src/main/resources/DB/sqlite-jdbc-3.40.0.0.jar}.
 * Il DDL del database si trova in {@code src/main/resources/DB/DDL.sql}.
 */
public class ConcreteDbManager implements DbManager {

    private Connection conn;

    /**
     * Costruttore per inizializzare il manager del database con una connessione.
     *
     * @param con Connessione al database SQLite.
     */
    ConcreteDbManager(Connection con) {
        conn = con;
    }

    /**
     * Esegue una query di tipo SELECT sul database e restituisce i risultati.
     *
     * @param query La query SQL da eseguire.
     * @return Una lista di mappe, dove ogni mappa rappresenta una riga del risultato, con le colonne come chiavi.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query.
     */
    @Override
    public final List<Map<String, Object>> queryExec(String query) throws SQLException {
        try {
            assert conn != null;

            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);

            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();

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
            System.out.println(e.getMessage()); // Mostra l'errore
            throw e;
        }
    }

    /**
     * Esegue un'operazione di inserimento (INSERT) nel database.
     *
     * @param SQL La query SQL di inserimento da eseguire.
     * @throws SQLException Se si verifica un errore durante l'esecuzione dell'inserimento.
     */
    @Override
    public void insert(String SQL) throws SQLException {

        assert conn != null;

        Statement ps = conn.createStatement();
        ps.executeUpdate(SQL);
    }
}
