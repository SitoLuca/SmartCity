package org.smartcity.smartcity.strategy;

import java.util.ArrayList;

/**
 * Rappresenta una strategia che definisce le azioni da intraprendere in risposta a una situazione specifica.
 * La strategia è utilizzata per determinare quali azioni devono essere eseguite in base alle circostanze
 * in una Smart City, come una gestione delle emergenze.
 *
 * I metodi implementati da questa interfaccia restituiscono un elenco di azioni che il sistema deve eseguire.
 */
public interface Strategy {

    /**
     * Esegue le azioni definite dalla strategia e restituisce una lista di stringhe contenente le azioni da intraprendere.
     * Ogni azione è rappresentata da una stringa nel formato "livello:messaggio".
     *
     * @return Una lista di stringhe contenente le azioni da eseguire.
     */
    ArrayList<String> act();
}
