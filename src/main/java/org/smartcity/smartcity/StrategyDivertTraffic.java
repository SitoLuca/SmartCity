package org.smartcity.smartcity;

public class StrategyDivertTraffic implements Strategy {

    @Override
    public void act() {

        System.out.println("Il traffico è stato deviato su un percorso alternativo");
    }
}
