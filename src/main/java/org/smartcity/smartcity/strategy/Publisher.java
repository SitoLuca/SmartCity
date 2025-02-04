package org.smartcity.smartcity.strategy;
/**
 * Interface for subscriber, useful for Observer implementation
 */
public interface Publisher {

    /**
     * Adds a subscriber to the publisher's list of subscribers.
     *
     * @param subscriber the subscriber that wants to receive notifications.
     */
    void subscribe(Subscriber subscriber);
    /**
     * Removes a subscriber from the publisher's list of subscribers.
     *
     * @param subscriber the subscriber that no longer wants to receive notifications.
     */
    void unsubscribe(Subscriber subscriber);
    /**
     * Notifies all subscribers about an event or message with a specific level and message content.
     *
     * @param level the level or severity of the message (e.g., "INFO", "WARING").
     * @param message the actual message or event description to be sent to the subscribers.
     */
    void notifySubscribers(String level, String message);

}
