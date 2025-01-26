package org.smartcity.smartcity;

public class Centralina {
    private final int id;
    private final String nome;
    private final String posizione;
    private Status status;

    public Centralina(String Nome, String Posizione, int Id) {
        id = Id;
        nome = Nome;
        posizione = Posizione;
        status = Status.unknown;
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

    public void setStatus(Status status) { this.status = status; }

}
