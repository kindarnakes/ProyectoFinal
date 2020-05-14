package Proyecto.controllers;

import Proyecto.App;
import Proyecto.model.Data;
import Proyecto.model.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
        if(this.img != null){
        String name = img.getName();
        if ((name.matches("(.*).jpg") || name.matches("(.*).png"))) {
            Image image = new Image(String.valueOf(img.toURI()));
            this.image.setImage(image);
        } else {

        }}
    }

    @FXML
    public void signUp() throws IOException {
        if (!user.getText().equals("") && !pass.getText().equals("")) {
            try {

                UserDAO.signUp(user.getText(), pass.getText(), email.getText(), date.getValue(), img != null ? new FileInputStream(img) : null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/Emergente.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro");
            Emergente mensaje = loader.getController();
            mensaje.setMensaje("Registo con éxito");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
