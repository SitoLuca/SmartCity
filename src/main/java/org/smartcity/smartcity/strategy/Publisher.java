package org.smartcity.smartcity.strategy;

public interface Publisher {

    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String level, String message);

}
