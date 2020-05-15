package Proyecto.controllers;

import Proyecto.Utils.Utils;
import Proyecto.model.ConnectionData;
import Proyecto.model.Data;
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
        bdname.setText("juegos");
        bdip.setText("localhost:3306");
        user.setText("root");
        pass.setText("10junio");

    }

    @FXML
    public void conectar() {
        ConnectionData conn = new ConnectionData(bdip.getText(), bdname.getText(), user.getText(), pass.getText());
        Data data = Data.getINSTANCE();
        data.set_conn(conn);
        try (Connection con = data.get_conn()) {

        } catch (SQLException ex) {

        }
        Utils.emergente("Conexión con la base de datos", "Conexión exitosa");


    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
    }

}
