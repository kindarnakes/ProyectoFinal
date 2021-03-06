package Proyecto.controllers;

import java.io.IOException;
import java.sql.Driver;

import Proyecto.App;
import Proyecto.Utils.Utils;
import Proyecto.model.Data;
import Proyecto.model.DriverConnection;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;


public class LoginController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView banner;

    @FXML
    public void initialize() {
        banner.setImage(new Image("/Banner.png"));
    }


    @FXML
    public void login() throws IOException {

        if (!usuario.getText().equals("") && !password.getText().equals("")) {
            if (Data.getINSTANCE().login(usuario.getText(), password.getText())) {
                App.setRoot("Profile");
            } else {
                Utils.emergente("Fallo de identificación", DriverConnection.getError());
            }
        } else {

            Utils.emergente("Fallo de identificación", "No puedes dejar campos vacíos");
        }
    }

    @FXML
    public void acerca() {
        Utils.emergente("Acerca de ...", "Trabajo realizado por: Ángel Serrano García\nPara el módulo de Programación del CFGS DAM\nIES Francisco de los Ríos\nProfesor: Carlos Serrano Sánchez");
    }

    @FXML
    public void openChooser() {
        Utils.newWindow("/views/chooseBD.fxml", "Elegir Base de Datos", Modality.APPLICATION_MODAL);
    }

    @FXML
    public void signUp() throws IOException {
        App.setRoot("SignUp");
    }


    @FXML
    public void close() {
        System.exit(0);
    }


}
