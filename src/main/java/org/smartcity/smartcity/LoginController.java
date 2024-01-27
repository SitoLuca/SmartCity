package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {
    @FXML
    private PasswordField psw;
    @FXML
    private TextField email;
    @FXML
    private Label CNV;

    public void Submit (ActionEvent e) throws SQLException {
        DbManager Db = new DbManager();
        System.out.println(Db.connect() ? "Connected": "Not connected");

        ResultSet res = Db.queryExec("Select * from user where email = '" + email.getText() +"' and password = '" + psw.getText() + "'");

        if (res.next()) {
            System.out.println("C'è qualcosa");
        }
        else {
            CNV.setOpacity(1.0);
        }

    }



}