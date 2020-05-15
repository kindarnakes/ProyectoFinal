package Proyecto.controllers;

import Proyecto.App;
import Proyecto.Utils.Utils;
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elige la imagen ...");
        this.img = fileChooser.showOpenDialog(image.getScene().getWindow());
        if (this.img != null) {
            if (this.img.length() < 60 * 1024) {
                String name = img.getName();
                if ((name.matches("(.*).jpg") || name.matches("(.*).png"))) {
                    Image image = new Image(String.valueOf(img.toURI()));
                    this.image.setImage(image);
                } else {
                }
            } else {
                Utils.emergente("Tamaño excedido", "Elija una imagen con tamaño menor");
            }
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

            Utils.emergente("Registro", "Registro con exito");
            if (done) {
                App.setRoot("Login");
            }


        }
    }

}
