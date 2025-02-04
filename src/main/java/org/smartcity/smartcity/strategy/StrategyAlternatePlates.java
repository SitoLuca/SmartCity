package org.smartcity.smartcity.strategy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of a strategy, allow the transit with altered plates
 *
 */

public class StrategyAlternatePlates implements Strategy {


    /**
     * Perform the strategy, allow the transit only with altered plates
     * Detect if someone breaks the rule (25% chance), if so, warn the police
     * @return ActionPerformed ArrayLis of the actions performed
     */
    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO:Emergenza Gestita a Targhe alterne");

        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1; // Generate Numer between 1 and 4

        if (randomNumber == 4){
            ActionPerformed.add("WARNING:Violazione rilevata");
            ActionPerformed.add("CRITICAL:Trasgressione segnalata alle autorità");
        }

       return ActionPerformed;
    }
}
