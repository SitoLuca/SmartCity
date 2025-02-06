package org.smartcity.smartcity;

import org.smartcity.smartcity.State.OfflineState;
import org.smartcity.smartcity.State.OnlineState;
import org.smartcity.smartcity.State.State;
import org.smartcity.smartcity.dbProxy.DbManagerProxy;
import org.smartcity.smartcity.enums.Codice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe che rappresenta un centralina all'interno del sistema Smart City.
 * Ogni centralina ha un identificativo, un nome, una posizione e una variabile statis che rappresenta il suo stato attuale.
 */
public class Centralin {
    private final int id; //Centraline unique id
    private final String name; //Name of centraline
    private final String position; //Position of centraline
    private State status; //Status of centraline (Online, Offline)
    private Codice codice; //Code of centraline (Green,Yellow, red)

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
        position = Posizione;
        status = new OfflineState();
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
    public String getPosition() {
        return position;
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
    public void setStatus(String status) {
        if (status.equals("offline")) {
            this.status = new OfflineState();
        }
        if (status.equals("online")) {
            this.status = new OnlineState();
        }
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

        return status.doaction(this.id);
    }
}
