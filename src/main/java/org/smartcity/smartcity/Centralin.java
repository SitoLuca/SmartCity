package org.smartcity.smartcity;

import org.smartcity.smartcity.dbProxy.DbManagerProxy;
import org.smartcity.smartcity.enums.Codice;
import org.smartcity.smartcity.enums.Status;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Centralin class represents a central unit that manages sensors for a smart city.
 * It holds information about the central unit's identity, position, status, and sensor readings.
 * The class is responsible for managing the state of the central unit, updating its status and code,
 * and detecting environmental parameters like pollution, temperature, and vehicle count when online.
 *
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
    private Status status; //Status of centraline (Online, Offline)
    private Codice codice; //Code of centraline (Green,Yellow, red)


    /**
     * Class Builder
     * @param name
     * @param position
     * @param Id
     */
    public Centralin(String name, String position, int Id) {
        id = Id;
        this.name = name;
        this.position = position;
        status = Status.offline;
        codice = Codice.Unknown;
    }

    /**
     * Getter name
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Getter position
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Getter status
     * @return status
     */
    public String getStatus() {
        return status.toString();
    }
    /**
     * Getter id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter codice
     * @return codice
     */
    public String getCodice() {
        return codice.toString();
    }

    /**
     * Setter status
     * @return status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Setter codice
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

        if (status == Status.offline) {
            System.out.println("Device offline");
        } else {
            DbManagerProxy manager = new DbManagerProxy();

            float inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100); //Generate Numbers
            float temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
            float n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

            String sql = "insert into log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) values (" + inquinamento + "," + temperatura + "," + n_vec + "," + this.id + ")";
            manager.insert(sql);

            Map<String, Float> params = new HashMap<String, Float>();

            params.put("Temperatura", temperatura); //Build the hash map with detections
            params.put("N_Veicoli", n_vec);
            params.put("inquinamento", inquinamento);

            return params;


        }

        return null;
    }

}
