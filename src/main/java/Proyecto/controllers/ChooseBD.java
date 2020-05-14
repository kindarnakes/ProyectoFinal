package Proyecto.controllers;

import Proyecto.App;
import Proyecto.model.ConnectionData;
import Proyecto.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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


        Parent root;
        try (Connection con = Data.getINSTANCE().get_conn()) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/Emergente.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Conexión");
            Emergente mensaje = loader.getController();
            mensaje.setMensaje("Conexión Exitosa");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) this.back.getScene().getWindow();
        stage.close();
    }

}
