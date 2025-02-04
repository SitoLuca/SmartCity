package org.smartcity.smartcity;

import org.smartcity.smartcity.dbProxy.DbManagerProxy;
import org.smartcity.smartcity.enums.Codice;
import org.smartcity.smartcity.enums.Status;
import org.smartcity.smartcity.dbProxy.ConcreteDbManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Centralina {
    private final int id;
    private final String nome;
    private final String posizione;
    private Status status;
    private Codice codice;

    public Centralina(String Nome, String Posizione, int Id) {
        id = Id;
        nome = Nome;
        posizione = Posizione;
        status = Status.offline;
        codice = Codice.Unknown;
    }

    public String getNome() {
        return nome;
    }

    public String getPosizione() {
        return posizione;
    }

    public String getStatus() {
        return status.toString();
    }

    public int getId() {
        return id;
    }

    public String getCodice() {
        return codice.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCodice(Codice codice) {
        this.codice = codice;
    }

    public Map<String, Float> detect() throws SQLException {

        if (status == Status.offline) {
            System.out.println("Device offline");
        } else {
            DbManagerProxy manager = new DbManagerProxy();

            float inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100);
            float temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
            float n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

            String sql = "insert into log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) values (" + inquinamento + "," + temperatura + "," + n_vec + "," + this.id + ")";
            manager.insert(sql);

            Map<String, Float> params = new HashMap<String, Float>();

            params.put("Temperatura", temperatura);
            params.put("N_Veicoli", n_vec);
            params.put("inquinamento", inquinamento);

            return params;


        }

        return null;
    }

}
