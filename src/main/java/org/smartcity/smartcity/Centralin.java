package org.smartcity.smartcity;

import org.smartcity.smartcity.dbProxy.DbManagerProxy;
import org.smartcity.smartcity.enums.Codice;
import org.smartcity.smartcity.enums.Status;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe che rappresenta un centralina all'interno del sistema Smart City.
 * Ogni centralina ha un identificativo, un nome, una posizione e una variabile statis che rappresenta il suo stato attuale.
 */
public class Centralin {
    private final int id;
    private final String name;
    private final String posizione;
    private Status status;
    private Codice codice;

    /**
     * Costruttore della classe Centralin.
     *
     * @param name      Nome della centralina.
     * @param Posizione Posizione della centralina.
     * @param Id        Identificativo univoco della centralina.
     */
    public Centralin(String name, String Posizione, int Id) {
        id = Id;
        this.name = name;
        posizione = Posizione;
        status = Status.offline;
        codice = Codice.Unknown;
    }

    /**
     * Restituisce il nome della centralina.
     *
     * @return Nome della centralina.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce la posizione della centralina.
     *
     * @return Posizione della centralina.
     */
    public String getPosizione() {
        return posizione;
    }

    /**
     * Restituisce lo stato attuale della centralina.
     *
     * @return Stato della centralina sotto forma di stringa.
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * Restituisce l'ID della centralina.
     *
     * @return Identificativo numerico della centralina.
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il codice associato al centralino.
     *
     * @return Codice della centralina sotto forma di stringa.
     */
    public String getCodice() {
        return codice.toString();
    }

    /**
     * Imposta lo stato della centralina.
     *
     * @param status Nuovo stato della centralina.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Imposta il codice della centralina.
     *
     * @param codice Nuovo codice della centralina.
     */
    public void setCodice(Codice codice) {
        this.codice = codice;
    }

    /**
     * Simula il rilevamento di parametri ambientali e li registra nel database.
     * Se il dispositivo è offline, stampa un messaggio di errore.
     *
     * @return Una mappa contenente i valori di temperatura, numero di veicoli e livello di inquinamento.
     * @throws SQLException Se si verifica un errore durante l'inserimento nel database.
     */
    public Map<String, Float> detect() throws SQLException {

        if (status == Status.offline) {
            System.out.println("Dispositivo offline");
        } else {
            DbManagerProxy manager = new DbManagerProxy();

            float inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100);
            float temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
            float n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

            String sql = "INSERT INTO log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) VALUES (" +
                    inquinamento + ", " + temperatura + ", " + n_vec + ", " + this.id + ")";
            manager.insert(sql);

            Map<String, Float> params = new HashMap<>();
            params.put("Temperatura", temperatura);
            params.put("N_Veicoli", n_vec);
            params.put("Inquinamento", inquinamento);

            return params;
        }
        return null;
    }
}
