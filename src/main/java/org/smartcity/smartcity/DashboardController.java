package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class DashboardController extends Controller {

    @FXML
    private TableView<Sensore> Table; //Ci andranno i risultati di una query
    @FXML
    private TableColumn<Sensore, String> Nome;
    @FXML
    private TableColumn<Sensore, String> Posizione;
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

        DbManager DB = new DbManager();
        ResultSet sensors = DB.queryExec("Select * from sensore");

    }

    @FXML
    private void initialize() throws FileNotFoundException {

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

        File soglie = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\SoglieDiGuardia.dat"); //Leggo il File che contiene le soglie di Guardia
        Scanner reader = new Scanner(soglie); //Definisco il reader del file

        String values = reader.nextLine(); //Leggo la prima riga del file
        String[] singleValues = values.split(","); //Divido i tre valori

        inquinamentoSoglia.setText(singleValues[0]); //Assegno i valori
        tempSoglia.setText(singleValues[1]);
        Nveicoli.setText(singleValues[2]);


    }


}
