package org.smartcity.smartcity.strategy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementazione del pattern Strategy per la gestione del traffico a targhe alterne.
 * Questa strategia viene utilizzata per ridurre il traffico e l'inquinamento
 * limitando la circolazione dei veicoli in base alla loro targa.
 */
public class StrategyAlternatePlates implements Strategy {

    /**
     * Esegue l'azione di limitazione del traffico a targhe alterne.
     * In alcuni casi, viene rilevata una violazione che viene segnalata alle autorità.
     *
     * @return Una lista contenente i messaggi di azione eseguita e eventuali segnalazioni di violazione.
     */
    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO: Emergenza gestita a targhe alterne");

        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1; // Genera un numero da 1 a 4

        if (randomNumber == 4) {
            ActionPerformed.add("WARNING: Violazione rilevata");
            ActionPerformed.add("CRITICAL: Trasgressione segnalata alle autorità");
        }

        return ActionPerformed;
    }
}
