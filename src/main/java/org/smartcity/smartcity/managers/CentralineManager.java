package org.smartcity.smartcity.managers;

import org.smartcity.smartcity.Centralin;
import org.smartcity.smartcity.enums.Codice;
import org.smartcity.smartcity.enums.Status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Gestisce un gruppo di centraline.
 * Questa classe è responsabile del monitoraggio dello status di ciascuna centralina
 * in base ai parametri ambientali e veicolari, e aggiorna lo status della centralina di conseguenza.
 */
public class CentralineManager {

    private static CentralineManager instance = null;
    private static ArrayList<Centralin> Centraline = new ArrayList<Centralin>();
    private float SogliaInquinamento;
    private float SogliaTemperatura;
    private int SogliaVeicoli;

    /**
     * Costruttore privato per impedire l'istanza dall'esterno.
     */
    private CentralineManager() {
    }

    /**
     * Imposta la soglia di inquinamento per le centraline.
     *
     * @param sogliaInquinamento Valore della soglia di inquinamento.
     */
    public void setSogliaInquinamento(float sogliaInquinamento) {
        SogliaInquinamento = sogliaInquinamento;
    }

    /**
     * Imposta la soglia di temperatura per le centraline.
     *
     * @param sogliatemperatura Valore della soglia di temperatura.
     */
    public void setSogliaTemperatura(float sogliatemperatura) {
        SogliaTemperatura = sogliatemperatura;
    }

    /**
     * Imposta la soglia di veicoli per le centraline.
     *
     * @param sogliaVeicoli Valore della soglia di veicoli.
     */
    public void setSogliaVeicoli(int sogliaVeicoli) {
        SogliaVeicoli = sogliaVeicoli;
    }

    /**
     * Restituisce la lista di tutte le centraline gestite da questo manager.
     *
     * @return Una lista di tutte le centraline.
     */
    public static ArrayList<Centralin> getCentraline() {
        return Centraline;
    }

    /**
     * Restituisce l'istanza singola del CentralineManager.
     *
     * @return L'istanza singola del CentralineManager.
     */
    public static CentralineManager getInstance() {
        return Objects.requireNonNullElseGet(instance, CentralineManager::new);
    }

    /**
     * Aggiunge una nuova centralina al manager.
     *
     * @param centralin La centralina da aggiungere.
     */
    public static void addCentralina(Centralin centralin) {
        Centraline.add(centralin);
    }

    /**
     * Attiva tutte le centraline impostando il loro status su online.
     */
    public void activateAll() {
        for (Centralin centralin : Centraline) {
            centralin.setStatus(Status.online);
        }
    }

    /**
     * Restituisce il numero totale di centraline gestite dal manager.
     *
     * @return Il numero totale di centraline.
     */
    public int getNumberOfCentraline() {
        return Centraline.size();
    }

    /**
     * Esegue il rilevamento per tutte le centraline e imposta il loro codice di status in base ai parametri di inquinamento, temperatura e veicoli.
     *
     * @throws SQLException Se si verifica un errore durante l'interazione con il database.
     */
    public void detectall() throws SQLException {
        for (Centralin centralin : Centraline) {
            Map<String, Float> ris = centralin.detect();

            if (countCriticParams(ris) <= 1) {
                centralin.setCodice(Codice.Verde);
            }
            if (countCriticParams(ris) == 2) {
                centralin.setCodice(Codice.Giallo);
            }
            if (countCriticParams(ris) == 3) {
                centralin.setCodice(Codice.Rosso);
            }

        }
    }

    /**
     * Conta il numero di parametri critici che superano le soglie impostate (inquinamento, temperatura, veicoli).
     *
     * @param params Una mappa contenente i parametri da valutare.
     * @return Il numero di parametri che superano la soglia.
     */
    private int countCriticParams(Map<String, Float> params){

        int n = 0;

        if (params.get("Temperatura") > SogliaTemperatura){
            n += 1;
        }
        if (params.get("inquinamento") > SogliaInquinamento){
            n += 1;
        }
        if (params.get("N_Veicoli") > SogliaVeicoli){
            n += 1;
        }

        return n;
    }
}
