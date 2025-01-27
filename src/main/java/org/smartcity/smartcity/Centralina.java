package org.smartcity.smartcity;

import java.sql.SQLException;
import java.util.Random;
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

    private void setCodice(Codice codice) {
        this.codice = codice;
    }

    public void detect() throws SQLException {

        if (status == Status.offline) {
            System.out.println("Device offline");
        } else {
            DbManager manager = DbManager.getInstance();

            int inquinamento = Math.abs(ThreadLocalRandom.current().nextInt() % 100);
            int temperatura = Math.abs(ThreadLocalRandom.current().nextInt() % 40);
            int n_vec = Math.abs(ThreadLocalRandom.current().nextInt() % 50);

            String sql = "insert into log_sensore (inquinamento, temperatura, n_veicoli, id_sensore) values (" + inquinamento + "," + temperatura + "," + n_vec + "," + this.id + ")";
            manager.insert(sql);

            Codice[] values = Codice.values(); // Ottieni tutti i valori dell'enum
            int randomIndex = new Random().nextInt(values.length);

            this.codice = values[randomIndex];

        }


    }

}
