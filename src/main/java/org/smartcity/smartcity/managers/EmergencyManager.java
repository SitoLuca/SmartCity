package org.smartcity.smartcity.managers;

import org.smartcity.smartcity.strategy.Publisher;
import org.smartcity.smartcity.strategy.Subscriber;
import org.smartcity.smartcity.strategy.Strategy;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Gestisce le emergenze nella Smart City, notificando gli abbonati con azioni in base alla strategia adottata.
 * Implementa il pattern Observer per gestire i sottoscrittori (Subscriber).
 */
public class EmergencyManager implements Publisher {

    private Strategy strategy;

    private static EmergencyManager instance = null;

    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    /**
     * Aggiunge un sottoscrittore alla lista degli abbonati.
     *
     * @param subscriber Il sottoscrittore da aggiungere.
     */
    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Rimuove un sottoscrittore dalla lista degli abbonati.
     *
     * @param subscriber Il sottoscrittore da rimuovere.
     */
    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notifica tutti i sottoscrittori con il livello di emergenza e il messaggio.
     *
     * @param level Il livello dell'emergenza.
     * @param message Il messaggio da inviare ai sottoscrittori.
     */
    @Override
    public void notifySubscribers(String level , String message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.Update(level, message);
        }
    }

    /**
     * Costruttore privato per prevenire la creazione di istanze esterne.
     */
    private EmergencyManager(){
    }

    /**
     * Restituisce l'istanza singola di EmergencyManager.
     *
     * @return L'istanza singola di EmergencyManager.
     */
    public static EmergencyManager getInstance() {
        return Objects.requireNonNullElseGet(instance, EmergencyManager::new);
    }

    /**
     * Imposta la strategia da seguire per gestire l'emergenza.
     *
     * @param strategy La strategia da applicare.
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        //notifySubscribers();
    }

    /**
     * Esegue le azioni della strategia corrente e notifica i sottoscrittori con i dettagli.
     * Ogni azione è rappresentata da una stringa contenente un livello e un messaggio separati da due punti.
     */
    public void act(){
        ArrayList<String> actions = strategy.act();

        for (String action : actions) {
            String[] levelMessage = action.split(":");

            notifySubscribers(levelMessage[0], levelMessage[1]);
        }
    }
}
