package org.smartcity.smartcity;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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

                String Final_line = nuovaSogliaInquinamento + "," + nuovaSogliaTemp + "," + nuovaSogliaNveicoli;

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\main\\resources\\SoglieDiGuardia.dat"));
                    writer.write(Final_line);
                    writer.close();
                    SoglieAggiornate.setOpacity(1.0);
                } catch (IOException e) {
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
                String sql = "Insert into sensore (nome,locazione) values ('" + Nome + "','" + Posizione + "');";
                System.out.println(sql);

                try {

                    db.insertExec(sql);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                newnome.setText("");
                newposizione.setText("");

                String Line = "Sensore: " + Nome + " Locazione: " + Posizione;

                Sensors.getItems().add(Line);


            }
        });

        setsoglie();

        putSensors();


    }

    private void putSensors() throws SQLException {

        DbManager DB = new DbManager();
        ResultSet AllSensors = DB.queryExec("Select * from sensore");
        //System.out.println(AllSensors.getString("Nome"));

        while (AllSensors.next()) {
            String Line = "Sensore: " + AllSensors.getString("nome") + " Locazione: " + AllSensors.getString("locazione");

            Sensors.getItems().add(Line);
        }


    }

    private void setsoglie() throws FileNotFoundException {
        File soglie = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\SoglieDiGuardia.dat"); //Leggo il File che contiene le soglie di Guardia
        Scanner reader = new Scanner(soglie); //Definisco il reader del file

        String values = reader.nextLine(); //Leggo la prima riga del file
        String[] singleValues = values.split(","); //Divido i tre valori

        inquinamentoSoglia.setText(singleValues[0]); //Assegno i valori
        tempSoglia.setText(singleValues[1]);
        Nveicoli.setText(singleValues[2]);
    }


}
