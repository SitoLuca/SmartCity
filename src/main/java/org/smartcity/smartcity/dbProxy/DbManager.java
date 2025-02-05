package org.smartcity.smartcity.dbProxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interfaccia che definisce le operazioni di accesso al database.
 * Questa interfaccia fornisce metodi per eseguire query SQL e per eseguire operazioni di inserimento
 * nel database. Viene utilizzata per interagire con un database relazionale (ad esempio, SQLite) in
 * un contesto di Smart City.
 */
public interface DbManager {

    /**
     * Esegue una query di tipo SELECT e restituisce i risultati sotto forma di una lista di mappe.
     * Ogni mappa rappresenta una riga del risultato, con le colonne come chiavi.
     *
     * @param query La query SQL da eseguire.
     * @return Una lista di mappe che rappresentano i risultati della query.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query.
     */
    List<Map<String, Object>> queryExec(String query) throws SQLException;

    /**
     * Esegue un'operazione di inserimento (INSERT) nel database.
     *
     * @param SQL La query SQL di inserimento da eseguire.
     * @throws SQLException Se si verifica un errore durante l'esecuzione dell'inserimento.
     */
    void insert(String SQL) throws SQLException;
}
