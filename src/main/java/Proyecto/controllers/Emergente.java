package Proyecto.controllers;

import Proyecto.model.DriverConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Emergente {

    @FXML
    private Button aceptar;
    @FXML
    protected Label estado;

    @FXML
    public void initialize() {

    }

    public void setMensaje(String s) {

        String error = "";
        String lines = s;
        while (lines.length() > 75) {
            error += lines.substring(0, 75) + "\n";
            lines = lines.substring(75);
        }
        error += lines;
        estado.setText(error);
    }

    @FXML
    public void back() {
        Stage stage = (Stage) this.aceptar.getScene().getWindow();
        stage.close();
    }

}
