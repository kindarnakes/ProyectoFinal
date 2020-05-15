package Proyecto.Utils;

import Proyecto.App;
import Proyecto.controllers.Emergente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {
    public static void emergente(String title, String messageNoError) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/Emergente.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            Emergente mensaje = loader.getController();
            mensaje.setMensaje(messageNoError);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnCloseRequest(ev -> {
                mensaje.finalize();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
