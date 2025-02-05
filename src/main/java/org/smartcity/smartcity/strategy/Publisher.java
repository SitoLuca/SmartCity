package org.smartcity.smartcity.strategy;

/**
 * Rappresenta un publisher che gestisce un gruppo di sottoscrittori (Subscriber).
 * Il publisher è responsabile per l'invio di notifiche agli abbonati, in base ai cambiamenti
 * di stato o eventi che si verificano nel sistema.
 *
 * I metodi implementati da questa interfaccia permettono di iscrivere e disiscrivere i sottoscrittori
 * e di notificare loro i cambiamenti.
 */
public interface Publisher {

    /**
     * Aggiunge un sottoscrittore alla lista degli abbonati.
     *
     * @param subscriber Il sottoscrittore da aggiungere.
     */
    void subscribe(Subscriber subscriber);

    /**
     * Rimuove un sottoscrittore dalla lista degli abbonati.
     *
     * @param subscriber Il sottoscrittore da rimuovere.
     */
    void unsubscribe(Subscriber subscriber);

    /**
     * Notifica tutti i sottoscrittori con il livello e il messaggio forniti.
     *
     * @param level Il livello dell'emergenza o della condizione (ad esempio, "Rosso", "Giallo", "Verde").
     * @param message Il messaggio dettagliato riguardante l'emergenza o la condizione.
     */
    void notifySubscribers(String level, String message);
}
