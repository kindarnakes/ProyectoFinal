module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Proyecto.model to mysql.connector.java;
    exports Proyecto;
    opens Proyecto to javafx.fxml;

    opens Proyecto.controllers to javafx.fxml;
    exports Proyecto.controllers;
}