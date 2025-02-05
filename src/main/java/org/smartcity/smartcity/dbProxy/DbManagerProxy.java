package org.smartcity.smartcity.dbProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Classe proxy per gestire le operazioni di accesso al database tramite il servizio {@link ConcreteDbManager}.
 * La classe si occupa di creare e gestire una connessione al database SQLite, delegando poi le operazioni
 * di query e inserimento al {@link ConcreteDbManager}.
 */
public class DbManagerProxy implements DbManager {

    private ConcreteDbManager Service;
    private Connection con = null;

    /**
     * Stabilisce una connessione al database SQLite utilizzando il driver JDBC.
     *
     * @return La connessione al database.
     * @throws SQLException Se si verifica un errore durante il tentativo di connessione.
     */
    private Connection connect() throws SQLException {
        String path = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\DB\\SmartCityDb.sqlite"; //Percorso del file del database, specificando il driver
        try {
            Connection conn = DriverManager.getConnection(path); //Connessione
            conn.setAutoCommit(true);
            return conn; //Connessione riuscita
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null; //Connessione fallita
        }
    }

    /**
     * Restituisce la connessione al database. Se la connessione è chiusa o nulla, viene creata una nuova connessione.
     *
     * @return La connessione al database.
     * @throws SQLException Se si verifica un errore durante la gestione della connessione.
     */
    private Connection getCon() throws SQLException {
        if (con == null) {
            con = connect();
            return con;
        }
        if (con.isClosed()) {
            con = connect();
            return con;
        }
        return con;
    }

    /**
     * Esegue una query di tipo SELECT sul database e restituisce i risultati.
     *
     * @param query La query SQL da eseguire.
     * @return Una lista di mappe che rappresentano i risultati della query.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query.
     */
    @Override
    public List<Map<String, Object>> queryExec(String query) throws SQLException {
        Service = new ConcreteDbManager(getCon());
        List<Map<String, Object>> res = Service.queryExec(query);
        CloseConnection();
        return res;
    }

    /**
     * Esegue un'operazione di inserimento (INSERT) nel database.
     *
     * @param SQL La query SQL di inserimento da eseguire.
     * @throws SQLException Se si verifica un errore durante l'esecuzione dell'inserimento.
     */
    @Override
    public void insert(String SQL) throws SQLException {
        Service = new ConcreteDbManager(getCon());
        Service.insert(SQL);
        CloseConnection();
    }

    /**
     * Chiude la connessione al database se è aperta.
     *
     * @throws SQLException Se si verifica un errore durante la chiusura della connessione.
     */
    private void CloseConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
