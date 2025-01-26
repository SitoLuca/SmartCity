package org.smartcity.smartcity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class DashboardController extends Controller {


    @FXML
    private TableView<Centralina> MatrixSensor;
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
    private Button creaGrafico;

    ObservableList<Centralina> Tabledata = FXCollections.observableArrayList();

    public DashboardController() {

    }

    @FXML
    private void initialize() throws FileNotFoundException, SQLException {

        salvaSoglie.setOnAction(new EventHandler<ActionEvent>() { //Quando viene cliccato il bottone "Salva soglie"
            @Override
            public void handle(ActionEvent actionEvent) {
                String nuovaSogliaInquinamento = inquinamentoSoglia.getText();
                String nuovaSogliaTemp = tempSoglia.getText();
                String nuovaSogliaNveicoli = Nveicoli.getText();

                DbManager db = DbManager.getInstance();

                try {

                    String sql = "insert into soglieDiGuardia (sogliaInquinamento, sogliaTemperatura, sogliaN_veicoli) values (" + nuovaSogliaInquinamento + ", " + nuovaSogliaTemp + "," + nuovaSogliaNveicoli + ")";
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

                DbManager db = DbManager.getInstance();

                List<Map<String, Object>> lastId;
                try {

                    db.insert("insert into Centralina (locazione,Nome) values ('" + Posizione + "', '" + Nome + "')");
                    lastId = db.queryExec("select max(id) as id from Centralina");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                newnome.setText("");
                newposizione.setText("");

                int id = Integer.parseInt(lastId.getFirst().get("id").toString());
                Tabledata.add(new Centralina(Nome, Posizione, id));

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

        MatrixSensor.getColumns().add(createColumn("Id", "id"));
        MatrixSensor.getColumns().add(createColumn("Nome", "nome"));
        MatrixSensor.getColumns().add(createColumn("Posizione", "posizione"));
        MatrixSensor.getColumns().add(createColumn("Status", "status"));

        DbManager DB = DbManager.getInstance();
        List<Map<String, Object>> AllSensors = DB.queryExec("Select * from Centralina");

        for (Map<String, Object> allSensor : AllSensors) {
            Tabledata.add(new Centralina(allSensor.get("Nome").toString(), allSensor.get("locazione").toString(), Integer.parseInt(allSensor.get("id").toString())));
        }

        MatrixSensor.setItems(Tabledata);

    }


    private TableColumn<Centralina, String> createColumn(String title, String property) {
        TableColumn<Centralina, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

    private void setsoglie() throws SQLException {

        DbManager db = DbManager.getInstance();

        List<Map<String, Object>> soglie = db.queryExec("select * from soglieDiGuardia where data_ora  = (select max(data_ora) from soglieDiGuardia)");

        inquinamentoSoglia.setText(soglie.getFirst().get("sogliaInquinamento").toString()); //Assegno i valori
        tempSoglia.setText(soglie.getFirst().get("sogliaTemperatura").toString());
        Nveicoli.setText(soglie.getFirst().get("sogliaN_veicoli").toString());
    }


}
