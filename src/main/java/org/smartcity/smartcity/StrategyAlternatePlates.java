package org.smartcity.smartcity;

public class StrategyAlternatePlates implements Strategy {

    @Override
    public void act() {
        System.out.println("Traffico consentito solo a targhe alterne");
    }
}
