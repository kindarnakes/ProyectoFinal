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
    private Label estado;

    @FXML
    public void initialize(){
        if(DriverConnection.getError().equals("")){
           estado.setText("Conexion creada con exito");
        }else{
            String error = "";
            String lines = DriverConnection.getError();
            while(lines.length() > 75){
                error += lines.substring(0,75) + "\n";
                lines = lines.substring(75);
            }
            error += lines;
            estado.setText(error);
        }
    }

    @FXML
    public void back(){
        Stage stage = (Stage) this.aceptar.getScene().getWindow();
        DriverConnection.setError("");
        stage.close();
    }
}
