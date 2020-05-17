package Proyecto.controllers;

import Proyecto.Utils.Utils;
import Proyecto.model.ConnectionData;
import Proyecto.model.Data;
import Proyecto.model.DriverConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class ChooseBD {

    @FXML
    private TextField bdname;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private TextField bdip;
    @FXML
    private Button back;

    @FXML
    public void initialize() {

    }

    @FXML
    public void conectar() {
        if (!bdip.getText().equals("") && !bdname.getText().equals("") && !user.getText().equals("") && !pass.getText().equals("")) {
            ConnectionData conn = new ConnectionData(bdip.getText(), bdname.getText(), user.getText(), pass.getText());
            Data data = Data.getINSTANCE();
            data.set_conn(conn);
            try (Connection con = data.get_conn()) {
                if (con != null) {
                    Utils.emergente("Conexión con la base de datos", "Conexión exitosa");
                } else {
                    Utils.emergente("Conexión con la base de datos", DriverConnection.getError());
                }
            } catch (SQLException ex) {
                Utils.emergente("Conexión con la base de datos", DriverConnection.getError());
            }
        } else {
            Utils.emergente("Conexión con la base de datos", "No puedes dejar campos vacíos");
        }


    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
    }

}
