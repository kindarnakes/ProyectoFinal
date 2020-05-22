package Proyecto.model;

import java.sql.*;

public class DriverConnection {

    private static String error = "";

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha podido iniciar el conector: " + ex);
        }
    }

    /**
     * Conecta a la base de datos especificada
     *
     * @param ip   la ip de la base de datos
     * @param bd   en nombre de la base de datos
     * @param user el usuario de la base de datos
     * @param pass la contraseña de la base de datos
     * @return la conexion realizada, o null si no se ha podido conectar
     */
    public static Connection Connection(String ip, String bd, String user, String pass) {
        loadDriver();
        Connection connect = null;
        try {
            String timezone = "?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid";
            connect = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd + timezone, user, pass);
            return connect;
        } catch (SQLException ex) {
            DriverConnection.error = "No se ha podido conectar a la Base de Datos: " + ex;
        }
        return connect;
    }

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        DriverConnection.error = error;
    }
}