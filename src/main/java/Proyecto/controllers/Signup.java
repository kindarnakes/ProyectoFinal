package Proyecto.controllers;

import Proyecto.App;
import Proyecto.Utils.Utils;
import Proyecto.model.DriverConnection;
import Proyecto.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Signup {

    @FXML
    private ImageView image;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField email;
    @FXML
    private DatePicker date;

    private File img;

    @FXML
    public void back() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void imageSelector() {
        Map<String, String> extensiones = new HashMap<>();
        extensiones.put("Imagen PNG (*.png)", "*.png");
        extensiones.put("Imagen JPG (*.jpg)", "*.jpg");
        this.img = Utils.fileChooser(extensiones, 60 * 1024);
        if (this.img != null) {
            Image image = new Image(String.valueOf(img.toURI()));
            this.image.setImage(image);
        } else {
            Utils.emergente("Tamaño excedido", "Elija una imagen válida\nTamaño inferior a 60 KB");
        }

    }

    @FXML
    public void signUp() throws IOException {
        boolean done = false;
        if (!user.getText().equals("") && !pass.getText().equals("")) {
            try {
                done = UserDAO.signUp(user.getText(), pass.getText(), email.getText(), date.getValue(), img != null ? new FileInputStream(img) : null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (done) {
                Utils.emergente("Registro", "Registro con exito");
                App.setRoot("Login");
            } else {
                Utils.emergente("Registro", DriverConnection.getError());
            }
        } else {
            Utils.emergente("Registro", "No puedes dejar campos obligatorios vacíos, Usuario y Contraseña");
        }
    }

}
