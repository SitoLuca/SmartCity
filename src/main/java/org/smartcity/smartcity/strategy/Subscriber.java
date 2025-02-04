package org.smartcity.smartcity.strategy;

/**
 * Interface for subscriber, useful for Observer implementation
 */
public interface Subscriber {

    /**
     * Subscriber gets Updated by publisher
     * @param level is the criticism of the message (info,waring,critical)
     * @param message is the message to show
     */
    void Update(String level,String message);

}
