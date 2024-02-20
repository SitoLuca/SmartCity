package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* Classe controller per la gestione dell' interfaccia di login,
* Estende la calsse Controller
*
* */
public class LoginController extends Controller {
    @FXML
    private PasswordField psw;
    @FXML
    private TextField email;
    @FXML
    private Label CNV;

    public void Submit(ActionEvent e) throws SQLException, IOException { //Event Listener click pulsante Submit
        DbManager Db = new DbManager();

        ResultSet res = Db.queryExec("Select * from user where email = '" + email.getText() + "' and password = '" + psw.getText() + "'"); //Costruisce la query in base all' input

        if (res.next()) { //Se c'è almeno un valore di ritorno
            res.close();
            super.SwapScene("Dashboard", "dashboard.fxml", 600, 600); //Passa alla dashboard

        } else {
            res.close();
            CNV.setOpacity(1.0); //Altrimenti mostra il messaggio di errore
        }

    }


}