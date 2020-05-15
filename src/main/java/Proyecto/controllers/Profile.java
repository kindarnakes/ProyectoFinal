package Proyecto.controllers;

import Proyecto.App;
import Proyecto.Utils.Utils;
import Proyecto.model.Data;
import Proyecto.model.User;
import Proyecto.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class Profile {
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
    @FXML
    private CheckBox deletecheck;

    private File img;


    @FXML
    public void initialize() {
        User u = Data.getINSTANCE().get_logged();
        user.setText(u.get_username());
        pass.setText(u.get_password());
        email.setText(u.get_email());
        date.setValue(u.get_born());
        Blob img = u.get_img();
        try {
            if (img != null) {
                image.setImage(new Image(img.getBinaryStream()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
    public void update() {
        User u = Data.getINSTANCE().get_logged();
        FileInputStream in = null;
        try {
            if (this.img != null) {
                in = new FileInputStream(this.img);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean done = UserDAO.userUpdate(user.getText(), pass.getText(), email.getText(), date.getValue(), in, u.get_username());
        Utils.emergente("Actualización", "Actualizado con exito");
        if (done) {
            Data.getINSTANCE().set_logged(UserDAO.getUserByNameAndPass(user.getText(), pass.getText()));
        }
    }

    @FXML
    public void delete() throws IOException {
        if (deletecheck.isSelected()) {
            boolean done = UserDAO.deleteUser(Data.getINSTANCE().get_logged().get_username());
            if (done) {
                Utils.emergente("Borrado", "Borrado con exito");
                App.setRoot("Login");
            } else {
                Utils.emergente("Borrado", "");
            }
        }
    }
}
