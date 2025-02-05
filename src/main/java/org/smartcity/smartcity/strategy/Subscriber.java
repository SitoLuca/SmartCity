package org.smartcity.smartcity.strategy;

/**
 * Rappresenta un sottoscrittore che riceve aggiornamenti in base ai cambiamenti di stato o messaggi.
 * Un sottoscrittore può essere una componente del sistema che reagisce a determinate notifiche
 * inviate dal {@link Publisher} (come un gestore di emergenze).
 *
 * I sottoscrittori implementano questo contratto per ricevere aggiornamenti con il livello e il messaggio
 * associati a un cambiamento di stato.
 */
public interface Subscriber {

    /**
     * Metodo chiamato per notificare il sottoscrittore di un cambiamento di stato.
     *
     * @param level Il livello dell'emergenza o della condizione (ad esempio, "Rosso", "Giallo", "Verde").
     * @param message Il messaggio dettagliato riguardante l'emergenza o la condizione.
     */
    void Update(String level, String message);
}
