package org.smartcity.smartcity.strategy;

import java.util.ArrayList;

/**
 * Impelementation of a strategy, this one divert traffic
 *
 */
public class StrategyDivertTraffic implements Strategy {
    /**
     * Perform the strategy, diverting traffic and return the actions performed
     *
     * @return ActionPerformed ArrayLis of the action performed, in this case only one action can be performed
     */
    @Override
    public ArrayList<String> act() {
        ArrayList<String> ActionPerformed = new ArrayList<>();
        ActionPerformed.add("INFO:Traffico deviato su un altro percorso");

        return ActionPerformed;

    }
}
