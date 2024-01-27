package org.smartcity.smartcity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController extends Controller {
    @FXML
    private PasswordField psw;
    @FXML
    private TextField email;
    @FXML
    private Label CNV;

    public void Submit(ActionEvent e) throws SQLException, IOException {
        DbManager Db = new DbManager();
        System.out.println(Db.connect() ? "Connected" : "Not connected");

        ResultSet res = Db.queryExec("Select * from user where email = '" + email.getText() + "' and password = '" + psw.getText() + "'");

        if (res.next()) {

            super.SwapScene("Dashboard", "dashboard.fxml", 600, 600);

        } else {
            CNV.setOpacity(1.0);
        }

    }


}