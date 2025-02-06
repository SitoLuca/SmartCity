package org.smartcity.smartcity.State;

import java.util.Map;

/**
 * Implementazione dello stato "offline" per un dispositivo.
 * <p>
 * Questa classe rappresenta lo stato in cui il dispositivo non è attivo o non
 * è connesso. Nel metodo {@link #doaction(int)} viene stampato un messaggio
 * che indica che il dispositivo è offline.
 * </p>
 */
public class OfflineState implements State {

    /**
     * Esegue l'azione associata allo stato offline.
     * <p>
     * In questo caso, viene stampato un messaggio che indica che il dispositivo
     * con l'identificativo specificato è offline. Non viene restituito alcun dato
     * utile (ritorna {@code null}).
     * </p>
     *
     * @param id l'identificativo del dispositivo
     * @return {@code null} poiché non sono previste operazioni o risultati in modalità offline
     */
    @Override
    public Map<String, Float> doaction(int id) {
        System.out.println("Device with id: " + id + " is offline");
        return null;
    }

    /**
     * Ritorna una rappresentazione testuale dello stato.
     * <p>
     * Questo metodo ritorna la stringa "offline" per indicare il tipo di stato.
     * </p>
     *
     * @return una stringa che rappresenta lo stato corrente
     */
    @Override
    public String toString() {
        return "offline";
    }
}
