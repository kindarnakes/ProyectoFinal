package Proyecto.Utils;

import Proyecto.App;
import Proyecto.controllers.Emergente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Utils {
    /**
     * Show modal window
     *
     * @param title   title of the new window
     * @param message message of the new window
     */
    public static void emergente(String title, String message) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/views/Emergente.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            Emergente mensaje = loader.getController();
            mensaje.setMensaje(message);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File fileChooser(Map<String, String> extensions, int maxBytes) {
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elige la imagen ...");
        if (extensions != null) {
            extensions.forEach((d, e) -> {
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter(d, e);
                fileChooser.getExtensionFilters().add(extFilter);
            });
        }
        file = fileChooser.showOpenDialog(new Stage());

        if (file != null && (maxBytes > 0 && file.length() > maxBytes)) {
            file = null;
        }
        return file;
    }

    public static void newWindow(String view, String title, Modality modal) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource(view));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(modal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object newWindowWithController(String view, String title, Modality modal) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(view));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(modal);
            controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }
}
