package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Classe controller per la gestione dell' interfaccia di login,
 * Estende la calsse Controller
 * <p>
 * */
public class LoginController extends Controller {
    @FXML
    private PasswordField psw;
    @FXML
    private TextField email;
    @FXML
    private Label CNV;

    public void Submit(ActionEvent e) throws SQLException, IOException { //Event Listener click pulsante Submit
        DbManager Db = DbManager.getInstance();;

        List<Map<String, Object>> res = Db.queryExec("Select * from user where email = '" + email.getText() + "' and password = '" + psw.getText() + "'"); //Costruisce la query in base all' input

        if (!res.isEmpty()) { //Se non è vuota
            super.SwapScene("Dashboard", "dashboard.fxml", 721, 693, false); //Passa alla dashboard

        } else {
            CNV.setOpacity(1.0); //Altrimenti mostra il messaggio di errore
        }
    }


}