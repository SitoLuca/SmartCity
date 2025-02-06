package org.smartcity.smartcity.State;

import java.util.Map;

public class OfflineState implements State {
    @Override
    public Map<String, Float> doaction(int id) {
        System.out.println("Device with id: " + id +" is offline");
        return null;
    }

    @Override
    public String toString() {
        return "offline";
    }
}
