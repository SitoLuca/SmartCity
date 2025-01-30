package org.smartcity.smartcity;

import java.util.ArrayList;

public class StrategyDivertTraffic implements Strategy {

    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO:Traffico deviato su un altro percorso");

        return ActionPerformed;

    }
}
