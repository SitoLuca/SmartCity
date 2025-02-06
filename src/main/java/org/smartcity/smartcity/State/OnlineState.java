package org.smartcity.smartcity.State;

import org.smartcity.smartcity.dbProxy.DbManagerProxy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class OnlineState implements State {

    @Override
    public Map<String, Float> doaction(int id )  {
        DbManagerProxy manager = new DbManagerProxy();

        float inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100); //Generate Numbers
        float temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
        float n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

        String sql = "insert into log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) values (" + inquinamento + "," + temperatura + "," + n_vec + "," + id + ")";

        try {
            manager.insert(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Map<String, Float> params = new HashMap<String, Float>();

        params.put("Temperatura", temperatura); //Build the hash map with detections
        params.put("N_Veicoli", n_vec);
        params.put("inquinamento", inquinamento);

        return params;
    }

    @Override
    public String toString() {
        return "online";
    }
}
