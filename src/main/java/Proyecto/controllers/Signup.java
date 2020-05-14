package Proyecto.controllers;

import Proyecto.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class Signup {


    @FXML
    public void back() throws IOException {
        App.setRoot("Login");
    }

}
