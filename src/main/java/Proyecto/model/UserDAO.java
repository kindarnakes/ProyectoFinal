package Proyecto.model;

import java.io.*;
import java.sql.*;

public class UserDAO extends User {

    public static User getUserByName(String user) {
        User u = null;
        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from user WHERE username = '?'";

            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, user);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    createUser(user, u, rs);
                    rs.close();
                }else{
                    DriverConnection.setError("No existe el usuario");
                }
            } catch (SQLException | IOException ex) {
                DriverConnection.setError("No se ha podido loggear " + ex);
            }
        }

        return u;
    }

    public static User getUserByNameAndPass(String user, String pass){
        User u = new User();
        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from user WHERE username = ? AND password = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, user);
                st.setString(2, pass);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    createUser(user, u, rs);
                    rs.close();
                }else{
                    DriverConnection.setError("No existe el usuario");
                }
            } catch (SQLException | IOException ex) {
                DriverConnection.setError("No se ha podido loggear " + ex);
            }
        }

        return u;
    }

    private static void createUser(String user, User u, ResultSet rs) throws SQLException, IOException {
        u.set_username(user);
        String pass = rs.getString("password");
        u.set_password(pass);
        u.set_email(rs.getString("email"));
        Date born = rs.getDate("born_date");
        u.set_born(born != null? born.toLocalDate() : null);
        Blob img = rs.getBlob("image");
        u.set_img(img);
    }

}
