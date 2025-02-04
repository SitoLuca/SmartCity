package org.smartcity.smartcity.strategy;

import java.util.ArrayList;
import java.util.Random;

public class StrategyAlternatePlates implements Strategy {

    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO:Emergenza Gestita a Targhe alterne");

        Random rand = new Random();
        int randomNumber = rand.nextInt(4) + 1; // Genera un numero da 1 a 4

        if (randomNumber == 4){
            ActionPerformed.add("WARNING:Violazione rilevata");
            ActionPerformed.add("CRITICAL:Trasgressione segnalata alle autorità");
        }

       return ActionPerformed;
    }
}
