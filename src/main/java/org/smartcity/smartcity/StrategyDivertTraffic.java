package org.smartcity.smartcity;

public class StrategyDivertTraffic implements Strategy {

    @Override
    public void act() {

        System.out.println("Il Traffico è stato deviato su un altro percorso");
    }
}
