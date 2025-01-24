package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class DashboardController extends Controller {


    @FXML
    private ListView<String> Sensors;
    @FXML
    private Button SalvaSensore;
    @FXML
    private TextField newnome;
    @FXML
    private TextField newposizione;
    @FXML
    private TextField inquinamentoSoglia;
    @FXML
    private TextField tempSoglia;
    @FXML
    private TextField Nveicoli;
    @FXML
    private Button salvaSoglie;
    @FXML
    private Label SoglieAggiornate;
    @FXML
    private DatePicker da;
    @FXML
    private DatePicker a;
    @FXML
    private Button creaGrafico;

    public DashboardController() throws SQLException {


    }

    @FXML
    private void initialize() throws FileNotFoundException, SQLException {

        salvaSoglie.setOnAction(new EventHandler<ActionEvent>() { //Quando viene cliccato il bottone "Salva soglie"
            @Override
            public void handle(ActionEvent actionEvent) {
                String nuovaSogliaInquinamento = inquinamentoSoglia.getText();
                String nuovaSogliaTemp = tempSoglia.getText();
                String nuovaSogliaNveicoli = Nveicoli.getText();

                DbManager db = new DbManager();

                try {

                    String sql = "insert into soglieDiGuardia (sogliaInquinamento, sogliaTemperatura, sogliaN_veicoli) values (" + nuovaSogliaInquinamento + ", " + nuovaSogliaTemp + ","+ nuovaSogliaNveicoli +")";
                    db.insert(sql);

                    SoglieAggiornate.setOpacity(1);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SalvaSensore.setOnAction(new EventHandler<ActionEvent>() { //Quando viene cliccato il bottone "Aggiungi"
            @Override
            public void handle(ActionEvent actionEvent) {

                String Nome = newnome.getText();
                String Posizione = newposizione.getText();

                DbManager db = new DbManager();

                try {

                    db.insert(Nome);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                newnome.setText("");
                newposizione.setText("");

                String Line = "Sensore: " + Nome + " Locazione: " + Posizione;

                Sensors.getItems().add(Line);


            }
        });

        creaGrafico.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Fai il grafico
            }
        });

        setsoglie();

        putSensors();


    }

    private void putSensors() throws SQLException {

        DbManager DB = new DbManager();
        List<Map<String, Object>> AllSensors = DB.queryExec("Select * from sensore");
        //System.out.println(AllSensors.getString("Nome"));

        for (Map<String, Object> allSensor : AllSensors) {

            String Line = "Sensore: " + allSensor.get("Nome") + " Locazione: " + allSensor.get("locazione");

            Sensors.getItems().add(Line);
        }


    }

    private void setsoglie() throws SQLException {

        DbManager db = new DbManager();

        List<Map<String, Object>> soglie = db.queryExec("select * from soglieDiGuardia where data_ora  = (select max(data_ora) from soglieDiGuardia)");

        inquinamentoSoglia.setText(soglie.getFirst().get("sogliaInquinamento").toString()); //Assegno i valori
        tempSoglia.setText(soglie.getFirst().get("sogliaTemperatura").toString());
        Nveicoli.setText(soglie.getFirst().get("sogliaN_veicoli").toString());
    }


}
