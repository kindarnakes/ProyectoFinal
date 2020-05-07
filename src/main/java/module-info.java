module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens Proyecto to javafx.fxml;
    exports Proyecto;
    opens Proyecto.controllers to javafx.fxml;
    exports Proyecto.controllers;
}