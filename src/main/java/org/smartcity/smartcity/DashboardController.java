package org.smartcity.smartcity;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController extends Controller {

    @FXML
    private TableView<Sensore> Table; //Ci andranno i risultati di una query
    @FXML
    private TableColumn<Sensore, String> Nome;
    @FXML
    private TableColumn<Sensore, String> Posizione;
    @FXML
    private TableColumn<Sensore, String> LastUpdate;
    @FXML
    private TableColumn<Sensore, String> Status;
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

    public DashboardController() throws SQLException {

        DbManager DB = new DbManager();

        ResultSet sensors = DB.queryExec("Select * from sensore");

        while (sensors.next()) {


        }


    }




}
