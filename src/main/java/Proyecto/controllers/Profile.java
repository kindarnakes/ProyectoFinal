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
import javafx.stage.Modality;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        this.deletecheck.getScene().getWindow().setHeight(420);
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
        } else {
            Utils.emergente("Borrado", "Debe marcar la casilla de confirmación para borrar");
        }
    }

    @FXML
    public void scores7YMedia() {
        Scores controller = (Scores) Utils.newWindowWithController("/views/Scores.fxml", "Tabla de puntuaciones 7 y media", Modality.APPLICATION_MODAL);
        controller.setGame("7ymedia");
        controller.setTableTitle("Tabla de puntuaciones de 7 y media");
        controller.show();
    }

    @FXML
    public void playIA7Media() throws IOException {
        App.setRoot("7yMedia");
    }

    @FXML
    public void historic() {
        Utils.newWindow("/views/Historic.fxml", "Historial de partidas", Modality.APPLICATION_MODAL);
    }

    @FXML
    public void notImplement(){
        Utils.emergente("No implementado aún", "Esta funcionalidad aún no esta implementada");
    }
}
