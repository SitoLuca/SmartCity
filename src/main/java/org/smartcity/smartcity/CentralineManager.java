package org.smartcity.smartcity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class CentralineManager {

    private static CentralineManager instance = null;
    private static ArrayList<Centralina> Centraline = new ArrayList<Centralina>();
    private float SogliaInquinamento;
    private float SogliaTemperatura;
    private int SogliaVeicoli;

    private CentralineManager() {
    }

    public void setSogliaInquinamento(float sogliaInquinamento) {
        SogliaInquinamento = sogliaInquinamento;
    }

    public void setSogliaTemperatura(float sogliatemperatura) {
        SogliaTemperatura = sogliatemperatura;
    }

    public void setSogliaVeicoli(int sogliaVeicoli) {
        SogliaVeicoli = sogliaVeicoli;
    }

    public static ArrayList<Centralina> getCentraline() {
        return Centraline;
    }

    public static CentralineManager getInstance() {
        return Objects.requireNonNullElseGet(instance, CentralineManager::new);
    }

    public static void addCentralina(Centralina centralina) {
        Centraline.add(centralina);
    }

    public void activateAll() {
        for (Centralina centralina : Centraline) {
            centralina.setStatus(Status.online);
        }
    }

    public int getNumberOfCentraline() {
        return Centraline.size();
    }

    public void detectall() throws SQLException {
        for (Centralina centralina : Centraline) {
            Map<String, Float> ris = centralina.detect();

            if (countCriticParams(ris) <= 1) {
                centralina.setCodice(Codice.Verde);
            }
            if (countCriticParams(ris) == 2) {
                centralina.setCodice(Codice.Giallo);
            }
            if (countCriticParams(ris) == 3) {
                centralina.setCodice(Codice.Rosso);
            }

        }
    }

    private int countCriticParams(Map<String, Float> params){

        int n = 0;

        if (params.get("Temperatura") > SogliaTemperatura){
            n += 1;
        }
        if (params.get("inquinamento") > SogliaInquinamento){
            n += 1;
        }
        if (params.get("N_Veicoli") > SogliaVeicoli){
            n += 1;
        }

        return n;
    }

}
