package org.smartcity.smartcity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.smartcity.smartcity.Centralin;
import org.smartcity.smartcity.dbProxy.DbManagerProxy;
import org.smartcity.smartcity.managers.CentralineManager;
import org.smartcity.smartcity.managers.EmergencyManager;
import org.smartcity.smartcity.strategy.StrategyAlternatePlates;
import org.smartcity.smartcity.strategy.StrategyDivertTraffic;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Controller della dashboard dell'applicazione SmartCity.
 *
 * Questa classe gestisce le operazioni sulla dashboard, come l'aggiunta di nuove centraline,
 * l'aggiornamento delle soglie di guardia, la visualizzazione di grafici, l'attivazione e l'aggiornamento delle centraline,
 * e la gestione di emergenze mediante l'uso di strategie.
 */
public class DashboardController extends Controller {

    // Elementi dell'interfaccia utente definiti tramite FXML

    /** Tabella che visualizza le centraline */
    @FXML
    private TableView<Centralin> MatrixSensor;

    /** Bottone per salvare una nuova centralina */
    @FXML
    private Button SalvaSensore;

    /** Campo di testo per il nome della centralina */
    @FXML
    private TextField newnome;

    /** Campo di testo per la posizione della centralina */
    @FXML
    private TextField newposizione;

    /** Campo di testo per inserire la soglia di inquinamento */
    @FXML
    private TextField inquinamentoSoglia;

    /** Campo di testo per inserire la soglia di temperatura */
    @FXML
    private TextField tempSoglia;

    /** Campo di testo per inserire la soglia di numero di veicoli */
    @FXML
    private TextField Nveicoli;

    /** Bottone per salvare le soglie di guardia */
    @FXML
    private Button salvaSoglie;

    /** Etichetta per indicare l'avvenuto aggiornamento delle soglie */
    @FXML
    private Label SoglieAggiornate;

    /** Bottone per creare il grafico */
    @FXML
    private Button creaGrafico;

    /** Bottone per avviare le centraline */
    @FXML
    private Button StartCentraline;

    /** Bottone per aggiornare lo stato delle centraline */
    @FXML
    private Button UpdateCentraline;

    /** RadioButton per selezionare la strategia di targhe alternate */
    @FXML
    private RadioButton RadioTargheAlternate;

    /** RadioButton per selezionare la strategia di deviazione del traffico */
    @FXML
    private RadioButton RadioDeviaTraffico;

    /** Bottone per visualizzare il log */
    @FXML
    private Button VisualizzaLog;

    /** Lista osservabile che contiene i dati per la tabella delle centraline */
    private ObservableList<Centralin> Tabledata = FXCollections.observableArrayList();

    /** Istanza del manager per le centraline */
    private final CentralineManager manager = CentralineManager.getInstance();

    /** Istanza del manager per le emergenze */
    private final EmergencyManager emergencyManager = EmergencyManager.getInstance();

    /** Menu contestuale per gestire operazioni sulle righe della tabella */
    ContextMenu contextMenu = new ContextMenu();

    /**
     * Costruttore della classe DashboardController.
     */
    public DashboardController() {
    }

    /**
     * Metodo di inizializzazione chiamato automaticamente al caricamento del file FXML.
     * Configura i gestori degli eventi per i vari componenti dell'interfaccia utente e
     * inizializza i dati visualizzati.
     *
     * @throws FileNotFoundException se un file necessario non viene trovato.
     * @throws SQLException se si verifica un errore durante l'interazione con il database.
     */
    @FXML
    private void initialize() throws FileNotFoundException, SQLException {

        // Gestione dell'evento per il bottone "Salva soglie"
        salvaSoglie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String nuovaSogliaInquinamento = inquinamentoSoglia.getText();
                String nuovaSogliaTemp = tempSoglia.getText();
                String nuovaSogliaNveicoli = Nveicoli.getText();

                DbManagerProxy db = new DbManagerProxy();

                try {
                    String sql = "insert into soglieDiGuardia (sogliaInquinamento, sogliaTemperatura, sogliaN_veicoli) values ("
                            + nuovaSogliaInquinamento + ", " + nuovaSogliaTemp + "," + nuovaSogliaNveicoli + ")";
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

        // Gestione dell'evento per il bottone "Aggiungi" (SalvaSensore)
        SalvaSensore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Nome = newnome.getText();
                String Posizione = newposizione.getText();

                DbManagerProxy db = new DbManagerProxy();
                List<Map<String, Object>> lastId;
                try {
                    db.insert("insert into Centralina (locazione,Nome) values ('" + Posizione + "', '" + Nome + "')");
                    lastId = db.queryExec("select max(id) as id from Centralina");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Pulizia dei campi di testo
                newnome.setText("");
                newposizione.setText("");

                // Creazione della nuova centralina e aggiornamento della tabella
                int id = Integer.parseInt(lastId.get(0).get("id").toString());
                Centralin newCentralin = new Centralin(Nome, Posizione, id);
                manager.addCentralina(newCentralin);
                Tabledata.add(newCentralin);
            }
        });

        // Gestione dell'evento per il bottone "Crea Grafico"
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

        // Gestione dell'evento per il bottone "Start Centraline"
        StartCentraline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manager.activateAll();
                try {
                    putSensors();
                    // Chiamata ripetuta (eventuale duplicazione) per aggiornare la tabella
                    putSensors();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                UpdateCentraline.setDisable(false);
                UpdateCentraline.setText("Aggiorna Centraline");
            }
        });

        // Gestione dell'evento per il bottone "Aggiorna Centraline"
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

        // Gestione dell'evento per il bottone "Visualizza Log"
        VisualizzaLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LogController logger = LogController.GetInstance();
                Stage loggerStage = new Stage();
                logger.start(loggerStage);
                emergencyManager.subscribe(logger);
            }
        });

        // Gestione degli eventi per i RadioButton delle strategie
        RadioTargheAlternate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (RadioTargheAlternate.isSelected()) {
                    RadioDeviaTraffico.setSelected(false);
                    emergencyManager.setStrategy(new StrategyAlternatePlates());
                } else {
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
                } else {
                    RadioDeviaTraffico.setSelected(true);
                }
            }
        });

        // Imposta la strategia di default per le emergenze
        emergencyManager.setStrategy(new StrategyAlternatePlates());

        // Configura il menu contestuale per la gestione delle righe della tabella
        MenuItem GestisciItem = new MenuItem("Gestisci");
        GestisciItem.setOnAction(event -> emergencyManager.act());
        contextMenu.getItems().add(GestisciItem);

        // Imposta il row factory per la tabella delle centraline, per mostrare il menu contestuale in caso di click destro
        MatrixSensor.setRowFactory(tv -> {
            TableRow<Centralin> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Centralin centralin = row.getItem();
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY && "Rosso".equalsIgnoreCase(centralin.getCodice())) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });
            return row;
        });

        // Imposta le soglie iniziali e popola la tabella dei sensori
        setsoglie();
        putSensors();
    }

    /**
     * Popola la tabella delle centraline (MatrixSensor) con i dati attuali.
     *
     * @throws SQLException Se si verifica un errore durante l'interazione con il database.
     */
    private void putSensors() throws SQLException {
        MatrixSensor.getColumns().clear();
        Tabledata.clear();

        // Aggiunge le colonne alla tabella
        MatrixSensor.getColumns().add(createColumn("Id", "id"));
        MatrixSensor.getColumns().add(createColumn("Nome", "nome"));
        MatrixSensor.getColumns().add(createColumn("Posizione", "posizione"));
        MatrixSensor.getColumns().add(createColumn("Status", "status"));
        MatrixSensor.getColumns().add(createColumn("Codice", "codice"));

        // Se non ci sono centraline gestite, le recupera dal database
        if (manager.getNumberOfCentraline() == 0) {
            DbManagerProxy db = new DbManagerProxy();
            List<Map<String, Object>> AllSensors = db.queryExec("Select * from Centralina");

            for (Map<String, Object> allSensor : AllSensors) {
                Centralin c = new Centralin(
                        allSensor.get("Nome").toString(),
                        allSensor.get("locazione").toString(),
                        Integer.parseInt(allSensor.get("id").toString())
                );
                manager.addCentralina(c);
                Tabledata.add(c);
            }
        } else {
            // Aggiunge tutte le centraline già gestite
            Tabledata.addAll(manager.getCentraline());
        }

        // Imposta i dati della tabella
        MatrixSensor.setItems(Tabledata);
    }

    /**
     * Crea una colonna per la tabella delle centraline.
     *
     * @param title    Il titolo della colonna.
     * @param property Il nome della proprietà dell'oggetto Centralin da visualizzare.
     * @return La colonna configurata.
     */
    private TableColumn<Centralin, String> createColumn(String title, String property) {
        TableColumn<Centralin, String> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));

        // Configura il cell factory per la colonna "status" per cambiare il colore del testo
        if (property.equalsIgnoreCase("status")) {
            col.setCellFactory(column -> new TableCell<Centralin, String>() {
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

        // Configura il cell factory per la colonna "codice" per mostrare un cerchio colorato
        if (property.equalsIgnoreCase("codice")) {
            col.setCellFactory(column -> new TableCell<Centralin, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                        setStyle("");
                    } else {
                        setText(item);
                        Circle circle = new Circle(8);
                        // Imposta il colore del cerchio in base al valore dell'enum
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
                        setGraphic(circle);
                    }
                }
            });
        }

        return col;
    }

    /**
     * Imposta le soglie di guardia iniziali recuperandole dal database
     * e aggiornando i campi di testo dell'interfaccia.
     *
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query.
     */
    private void setsoglie() throws SQLException {
        DbManagerProxy db = new DbManagerProxy();
        List<Map<String, Object>> soglie = db.queryExec("select * from soglieDiGuardia where data_ora = (select max(data_ora) from soglieDiGuardia)");

        // Imposta le soglie nel manager
        manager.setSogliaInquinamento(Float.parseFloat(soglie.get(0).get("sogliaInquinamento").toString()));
        manager.setSogliaTemperatura(Float.parseFloat(soglie.get(0).get("sogliaTemperatura").toString()));
        manager.setSogliaVeicoli(Integer.parseInt(soglie.get(0).get("sogliaN_veicoli").toString()));

        // Aggiorna i campi di testo con le soglie recuperate
        inquinamentoSoglia.setText(soglie.get(0).get("sogliaInquinamento").toString());
        tempSoglia.setText(soglie.get(0).get("sogliaTemperatura").toString());
        Nveicoli.setText(soglie.get(0).get("sogliaN_veicoli").toString());
    }
}
