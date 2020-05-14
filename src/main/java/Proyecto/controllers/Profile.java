package Proyecto.controllers;

import Proyecto.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class Profile {

    @FXML
    public void back() throws IOException {
        App.setRoot("Login");
    }
}
