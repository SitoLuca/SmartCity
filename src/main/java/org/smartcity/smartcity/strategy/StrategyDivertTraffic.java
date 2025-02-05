package org.smartcity.smartcity.strategy;

import java.util.ArrayList;

/**
 * Implementazione della pattern Strategy per deviare il traffico.
 * Questa strategia viene utilizzata per ridurre la congestione stradale
 * deviando il traffico su percorsi alternativi.
 */
public class StrategyDivertTraffic implements Strategy {

    /**
     * Esegue l'azione di deviazione del traffico.
     *
     * @return Una lista contenente il messaggio di azione eseguita.
     */
    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO: Traffico deviato su un altro percorso");

        return ActionPerformed;
    }
}
