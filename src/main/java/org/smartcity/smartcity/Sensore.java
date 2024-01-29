package org.smartcity.smartcity;

public class Sensore {

    private int Id;
    private String Nome;
    private String Locazione;

    public String getNome() {
        return this.Nome;
    }

    public String getLocazione() {
        return this.Locazione;
    }

    public int getid() {
        return this.Id;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setLocazione(String locazione) {
        Locazione = locazione;
    }

    public void setid(int id) {
        Id = id;
    }
}
