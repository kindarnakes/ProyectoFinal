module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires org.apache.commons.io;

    opens Proyecto.model to mysql.connector.java;
    exports Proyecto;
    opens Proyecto to javafx.fxml;

    opens Proyecto.controllers to javafx.fxml;
    exports Proyecto.controllers;
}