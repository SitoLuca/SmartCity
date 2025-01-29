package org.smartcity.smartcity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class EmergencyManager implements Publisher{
    private Strategy strategy;

    private static EmergencyManager instance = null;

    private ArrayList<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.Update("INFO", "Ciao");
        }
    }

    private EmergencyManager(){

    }

    public static EmergencyManager getInstance() {
        return Objects.requireNonNullElseGet(instance, EmergencyManager::new);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        notifySubscribers();
    }

    public void act(){
        strategy.act();
        notifySubscribers();
    }

}
