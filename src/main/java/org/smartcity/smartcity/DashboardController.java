package org.smartcity.smartcity;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

//import java.awt.*;
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
    @FXML
    private Button StartCentraline;
    @FXML
    private Button UpdateCentraline;
    @FXML
    private RadioButton RadioTargheAlternate;
    @FXML
    private RadioButton RadioDeviaTraffico;
    @FXML
    private Button VisualizzaLog;


    private ObservableList<Centralina> Tabledata = FXCollections.observableArrayList();
    private final CentralineManager manager = CentralineManager.getInstance();
    private final EmergencyManager emergencyManager = EmergencyManager.getInstance();
    ContextMenu contextMenu = new ContextMenu();

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
                    manager.setSogliaInquinamento(Float.parseFloat(nuovaSogliaInquinamento));
                    manager.setSogliaTemperatura(Float.parseFloat(nuovaSogliaTemp));
                    manager.setSogliaVeicoli(Integer.parseInt(nuovaSogliaNveicoli));
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
                Centralina newCentralina = new Centralina(Nome, Posizione, id);
                manager.addCentralina(newCentralina);
                Tabledata.add(newCentralina);

            }
        });

        creaGrafico.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlotController plot = new PlotController();
                try {
                    plot.start(new Stage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        StartCentraline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                manager.activateAll();
                try {
                    putSensors();
                    putSensors();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                UpdateCentraline.setDisable(false);
                UpdateCentraline.setText("Aggiorna Centraline");
            }
        });

        UpdateCentraline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    manager.detectall();
                    putSensors();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        VisualizzaLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LogController logger = LogController.GetInstance();
                Stage loggerStage = new Stage();
                logger.start(loggerStage);

                emergencyManager.subscribe(logger);

            }
        });

        RadioTargheAlternate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (RadioTargheAlternate.isSelected()) {
                    RadioDeviaTraffico.setSelected(false);
                    emergencyManager.setStrategy(new StrategyAlternatePlates());
                    //emergencyManager.act();
                }else {
                    RadioTargheAlternate.setSelected(true);
                }

            }
        });

        RadioDeviaTraffico.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (RadioDeviaTraffico.isSelected()) {
                    RadioTargheAlternate.setSelected(false);
                    emergencyManager.setStrategy(new StrategyDivertTraffic());
                    //emergencyManager.act();
                }else {
                    RadioDeviaTraffico.setSelected(true);
                }
            }
        });

        emergencyManager.setStrategy(new StrategyAlternatePlates());

        MenuItem GestisciItem = new MenuItem("Gestisci");
        GestisciItem.setOnAction(event -> emergencyManager.act());
        contextMenu.getItems().add(GestisciItem);

        MatrixSensor.setRowFactory(tv -> {
            TableRow<Centralina> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Centralina centralina = row.getItem();
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY && "Rosso".equalsIgnoreCase(centralina.getCodice())) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });
            return row;
        });

        setsoglie();

        putSensors();

    }

    private void putSensors() throws SQLException {
        MatrixSensor.getColumns().clear();
        Tabledata.clear();

        MatrixSensor.getColumns().add(createColumn("Id", "id"));
        MatrixSensor.getColumns().add(createColumn("Nome", "nome"));
        MatrixSensor.getColumns().add(createColumn("Posizione", "posizione"));
        MatrixSensor.getColumns().add(createColumn("Status", "status"));

        MatrixSensor.getColumns().add(createColumn("Codice", "codice"));

        if (manager.getNumberOfCentraline() == 0) {
            DbManager DB = DbManager.getInstance();
            List<Map<String, Object>> AllSensors = DB.queryExec("Select * from Centralina");

            for (Map<String, Object> allSensor : AllSensors) {
                Centralina c = new Centralina(allSensor.get("Nome").toString(), allSensor.get("locazione").toString(), Integer.parseInt(allSensor.get("id").toString()));
                manager.addCentralina(c);
                Tabledata.add(c);
            }
        } else {

            Tabledata.addAll(manager.getCentraline());
        }

        MatrixSensor.setItems(Tabledata);

    }

    private TableColumn<Centralina, String> createColumn(String title, String property) {
        TableColumn<Centralina, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));

        if (property.equalsIgnoreCase("status")) {
            col.setCellFactory(column -> new TableCell<Centralina, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);

                        if (item.equalsIgnoreCase("offline")) {
                            setStyle("-fx-text-fill: red;");
                        } else {
                            setStyle("-fx-text-fill: green;");
                        }
                    }
                }
            });
        }

        if (property.equalsIgnoreCase("codice")) {
            col.setCellFactory(column -> new TableCell<Centralina, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                        setStyle("");
                    } else {
                        setText(item); // Mostra il nome dell'enum come testo
                        Circle circle = new Circle(8);

                        // Imposta lo stile e il colore in base al valore dell'enum
                        switch (item) {
                            case "Verde":
                                circle.setFill(Color.GREEN);
                                break;
                            case "Rosso":
                                circle.setFill(Color.RED);
                                break;
                            case "Giallo":
                                circle.setFill(Color.YELLOW);
                                break;
                            case "Unknown":
                                circle.setFill(Color.GRAY);
                                break;
                            default:
                                circle.setFill(Color.BLACK);
                                break;
                        }
                        setGraphic(circle); // Imposta il cerchio come grafica della cella
                    }
                }
            });
        }

        return col;
    }

    private void setsoglie() throws SQLException {

        DbManager db = DbManager.getInstance();

        List<Map<String, Object>> soglie = db.queryExec("select * from soglieDiGuardia where data_ora  = (select max(data_ora) from soglieDiGuardia)");

        manager.setSogliaInquinamento(Float.parseFloat(soglie.getFirst().get("sogliaInquinamento").toString()));
        manager.setSogliaTemperatura(Float.parseFloat(soglie.getFirst().get("sogliaTemperatura").toString()));
        manager.setSogliaVeicoli(Integer.parseInt(soglie.getFirst().get("sogliaN_veicoli").toString()));

        inquinamentoSoglia.setText(soglie.getFirst().get("sogliaInquinamento").toString()); //Assegno i valori
        tempSoglia.setText(soglie.getFirst().get("sogliaTemperatura").toString());
        Nveicoli.setText(soglie.getFirst().get("sogliaN_veicoli").toString());
    }


}
