package org.smartcity.smartcity;

import org.smartcity.smartcity.State.OfflineState;
import org.smartcity.smartcity.State.OnlineState;
import org.smartcity.smartcity.State.State;
import org.smartcity.smartcity.enums.Codice;

import java.sql.SQLException;
import java.util.Map;

/**
 * The Centralin class represents a central unit that manages sensors for a smart city.
 * It holds information about the central unit's identity, position, status, and sensor readings.
 * The class is responsible for managing the state of the central unit, updating its status and code,
 * and detecting environmental parameters like pollution, temperature, and vehicle count when online.
 * <p>
 * Responsibilities:
 * - Maintain the central unit's identity (id), name, position, status, and associated code.
 * - Manage the unit's online/offline status.
 * - Generate and log environmental data readings when online.
 * - Insert the generated sensor data (pollution, temperature, vehicle count) into a database.
 * - Provide getter methods for retrieving the central unit's details.
 */

public class Centralin {
    private final int id; //Centraline unique id
    private final String name; //Name of centraline
    private final String position; //Position of centraline
    private State status; //Status of centraline (Online, Offline)
    private Codice codice; //Code of centraline (Green,Yellow, red)


    /**
     * Class Builder
     *
     * @param name
     * @param position
     * @param Id
     */
    public Centralin(String name, String position, int Id) {
        id = Id;
        this.name = name;
        this.position = position;
        status = new OfflineState();
        codice = Codice.Unknown;
    }

    /**
     * Getter name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Getter position
     *
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Getter status
     *
     * @return status
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * Getter id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter codice
     *
     * @return codice
     */
    public String getCodice() {
        return codice.toString();
    }

    /**
     * Setter status
     *
     * @return status
     */
    public void setStatus(String status) {
        if (status.equals("offline")) {
            this.status = new OfflineState();
        }
        if (status.equals("online")) {
            this.status = new OnlineState();
        }
    }

    /**
     * Setter codice
     *
     * @return codice
     */
    public void setCodice(Codice codice) {
        this.codice = codice;
    }

    /**
     * Checks if device is Online
     * Used for centraline detection, generate (randomly) the values of pollution,temperaature
     * and store them in to database
     *
     * @return Map<String, Float> Rappresents the detection as <String> is the map index and <Float> the detection value
     * @throws SQLException
     */
    public Map<String, Float> detect() throws SQLException {

        return status.doaction(this.id);

    }

}
