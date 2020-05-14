package Proyecto.controllers;

import java.io.IOException;

import Proyecto.App;
import Proyecto.model.Data;
import Proyecto.model.User;
import Proyecto.model.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView banner;


    @FXML
    public void login() throws IOException {
        User u = null;
        u = UserDAO.getUserByNameAndPass(usuario.getText(), password.getText());
        if (u != null && password.getText().equals(u.get_password())) {
            Data.getINSTANCE().set_logged(u);
            App.setRoot("Profile");
        } else {
            Parent root;
            try {
                root = FXMLLoader.load(App.class.getResource("/views/Emergente.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Fallo de identificación");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException e) {

            }

        }
    }

    @FXML
    public void openChooser() {
        Parent root;
        try {
            root = FXMLLoader.load(App.class.getResource("/views/chooseBD.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Elegir Base de Datos");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
