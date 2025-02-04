package org.smartcity.smartcity.strategy;

import java.util.ArrayList;
/**
 * Interface for strategies, useful for Strategy implementation
 */
public interface Strategy {
    /**
     * Executes the strategy and returns a list of actions performed.
     *
     * @return a list of strings representing the actions taken by the strategy.
     */
    ArrayList<String> act();

}
