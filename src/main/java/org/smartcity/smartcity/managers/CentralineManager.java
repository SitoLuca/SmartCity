package org.smartcity.smartcity.managers;

import org.smartcity.smartcity.Centralin;
import org.smartcity.smartcity.enums.Codice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class CentralineManager {

    private static CentralineManager instance = null;
    private static ArrayList<Centralin> Centraline = new ArrayList<Centralin>();
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

    public static ArrayList<Centralin> getCentraline() {
        return Centraline;
    }

    public static CentralineManager getInstance() {
        return Objects.requireNonNullElseGet(instance, CentralineManager::new);
    }

    public static void addCentralina(Centralin centralin) {
        Centraline.add(centralin);
    }

    public void activateAll() {
        for (Centralin centralin : Centraline) {
            centralin.setStatus("online");
        }
    }

    public int getNumberOfCentraline() {
        return Centraline.size();
    }

    public void detectall() throws SQLException {
        for (Centralin centralin : Centraline) {
            Map<String, Float> ris = centralin.detect();
            if (ris != null) {
                if (countCriticParams(ris) <= 1) {
                    centralin.setCodice(Codice.Verde);
                }
                if (countCriticParams(ris) == 2) {
                    centralin.setCodice(Codice.Giallo);
                }
                if (countCriticParams(ris) == 3) {
                    centralin.setCodice(Codice.Rosso);
                }
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
