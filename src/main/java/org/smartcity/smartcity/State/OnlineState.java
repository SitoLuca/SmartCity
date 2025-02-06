package org.smartcity.smartcity.State;

import org.smartcity.smartcity.dbProxy.DbManagerProxy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementazione dello stato "online" per un dispositivo.
 * <p>
 * In questo stato, il dispositivo esegue l'azione di generare dati casuali relativi a inquinamento,
 * temperatura e numero di veicoli, registra tali dati nel database tramite un {@link DbManagerProxy} e
 * restituisce i valori generati sotto forma di mappa.
 * </p>
 */
public class OnlineState implements State {

    /**
     * Esegue l'azione associata allo stato online.
     * <p>
     * Questo metodo genera valori casuali per i parametri:
     * <ul>
     *   <li>inquinamento (valore compreso tra 0 e 99)</li>
     *   <li>temperatura (valore compreso tra 0 e 39)</li>
     *   <li>numero di veicoli (valore compreso tra 0 e 49)</li>
     * </ul>
     * Successivamente, inserisce tali dati nel database tramite un'istruzione SQL.
     * In caso di errore durante l'inserimento, viene stampato lo stack trace dell'eccezione.
     * Infine, i dati generati vengono inseriti in una {@link Map} e restituiti.
     * </p>
     *
     * @param id l'identificativo del sensore/dispositivo
     * @return una {@link Map} contenente le chiavi "Temperatura", "N_Veicoli" e "inquinamento" con i relativi valori generati
     */
    @Override
    public Map<String, Float> doaction(int id)  {
        DbManagerProxy manager = new DbManagerProxy();

        // Genera valori casuali per i parametri
        float inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100);
        float temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
        float n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

        // Costruzione della query SQL per l'inserimento dei dati nel database
        String sql = "insert into log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) values ("
                + inquinamento + "," + temperatura + "," + n_vec + "," + id + ")";

        // Esecuzione della query e gestione delle possibili eccezioni
        try {
            manager.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creazione e popolamento della mappa con i dati generati
        Map<String, Float> params = new HashMap<>();
        params.put("Temperatura", temperatura);
        params.put("N_Veicoli", n_vec);
        params.put("inquinamento", inquinamento);

        return params;
    }

    /**
     * Restituisce una rappresentazione testuale dello stato.
     * <p>
     * Questo metodo ritorna la stringa "online" per indicare il tipo di stato.
     * </p>
     *
     * @return una stringa che rappresenta lo stato corrente ("online")
     */
    @Override
    public String toString() {
        return "online";
    }
}
