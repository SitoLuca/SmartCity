package org.smartcity.smartcity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.smartcity.smartcity.dbProxy.ConcreteDbManager;
import org.smartcity.smartcity.dbProxy.DbManagerProxy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Controller per la gestione dell'interfaccia di login.
 * Estende la classe {@link Controller}.
 *
 * Questa classe gestisce il comportamento del modulo di login,
 * verificando le credenziali dell'utente nel database.
 */
public class LoginController extends Controller {
    @FXML
    private PasswordField psw; // Campo per la password
    @FXML
    private TextField email;   // Campo per l'email
    @FXML
    private Label CNV;         // Etichetta per il messaggio di errore

    /**
     * Metodo chiamato quando l'utente clicca sul pulsante "Submit".
     * Esegue la verifica delle credenziali nel database.
     *
     * @param e L'evento che scatta al click del pulsante.
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query SQL.
     * @throws IOException Se si verifica un errore durante la gestione del cambio di scena.
     */
    public void Submit(ActionEvent e) throws SQLException, IOException {
        // Creazione di un oggetto DbManagerProxy per gestire la connessione al database
        DbManagerProxy Db = new DbManagerProxy();

        // Esecuzione della query per verificare la corrispondenza delle credenziali
        List<Map<String, Object>> res = Db.queryExec(
                "Select * from user where email = '" + email.getText() + "' and password = '" + psw.getText() + "'"
        );

        // Se la query restituisce risultati (utente trovato)
        if (!res.isEmpty()) {
            // Cambio scena per passare alla dashboard
            super.SwapScene("Dashboard", "dashboard.fxml", 721, 693, false);
        } else {
            // Altrimenti, mostra il messaggio di errore
            CNV.setOpacity(1.0);
        }
    }
}
