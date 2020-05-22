package Proyecto.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Base64;

public class UserDAO extends User {


    public static User getUserByNameAndPass(String user, String pass) {
        User u = null;
        String password = "";
        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from user WHERE username = ? AND password = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                password = cipher(pass);
                st.setString(1, user);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    u = new User();
                    createUser(user, pass, u, rs);
                    rs.close();
                } else {
                    DriverConnection.setError("No existe el usuario");
                }
            } catch (SQLException | IOException | NoSuchAlgorithmException ex) {
                DriverConnection.setError("No se ha podido loggear " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

        return u;
    }

    public static User getUserByName(String user) {
        User u = null;
        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from user WHERE username = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, user);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    u = new User();
                    createUser(user, "", u, rs);
                    rs.close();
                }
            } catch (SQLException | IOException ex) {
                DriverConnection.setError("Error " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }

        return u;
    }

    private static String cipher(String pass) throws NoSuchAlgorithmException {
        MessageDigest cipher = MessageDigest.getInstance("SHA-256");
        byte[] encodedpass = cipher.digest(pass.getBytes(StandardCharsets.UTF_8));
        String password = Base64.getEncoder().encodeToString(encodedpass);
        return password;
    }

    private static void createUser(String user, String pass, User u, ResultSet rs) throws SQLException, IOException {
        u.set_username(user);
        u.set_password(pass);
        u.set_email(rs.getString("email"));
        Date born = rs.getDate("born_date");
        u.set_born(born != null ? born.toLocalDate() : null);
        Blob img = rs.getBlob("image");
        u.set_img(img);
    }

    public static boolean signUp(String _username, String _password, String _email, LocalDate _born, FileInputStream _img) {
        boolean signup = false;
        String sql = "INSERT INTO user(username, password, email, born_date, image) VALUES(?,?,?,?,?)";

        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                _password = cipher(_password);
                st.setString(1, _username);
                st.setString(2, _password);
                st.setString(3, _email);
                st.setDate(4, _born != null ? Date.valueOf(_born) : null);
                st.setBlob(5, _img);
                Integer rs = st.executeUpdate();
                if (rs != null && rs == 1) {
                    signup = true;
                } else {
                    DriverConnection.setError("Existe el usuario");
                }
            } catch (SQLException | NoSuchAlgorithmException ex) {
                DriverConnection.setError("No se ha podido registrar " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return signup;
    }

    private static boolean signUpSpecial(String _username) {
        boolean signup = false;
        String sql = "INSERT INTO user(username, password) VALUES(?,?)";

        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, _username);
                st.setString(2, "");
                Integer rs = st.executeUpdate();
                if (rs != null && rs == 1) {
                    signup = true;
                }

            } catch (SQLException ex) {
                DriverConnection.setError("No se ha podido registrar " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return signup;
    }


    public static boolean userUpdate(String _username, String _password, String _email, LocalDate _born, FileInputStream _img, String oldUser) {
        boolean update = false;
        String sql = "UPDATE user SET username = ?, password = ?, email = ?, born_date = ?, image = ? WHERE username = ?";
        if (oldUser != null && _username != null && _password != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    String password = cipher(_password);
                    st.setString(1, _username);
                    st.setString(2, password);
                    st.setString(3, _email);
                    st.setDate(4, _born != null ? Date.valueOf(_born) : null);
                    if (_img != null) {
                        st.setBlob(5, _img);
                    } else {
                        Blob b = null;
                        if (Data.getINSTANCE().get_logged() != null) {
                            b = Data.getINSTANCE().get_logged().get_img();
                        }
                        st.setBlob(5, b);
                    }
                    st.setString(6, oldUser);
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        update = true;
                    }
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    DriverConnection.setError("No se ha podido actualizar " + ex);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        return update;
    }

    public static boolean deleteUser(String username) {
        boolean done = false;
        String sql = "DELETE FROM user WHERE username = ?";
        if (username != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, username);
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        done = true;
                    }
                } catch (SQLException ex) {
                    DriverConnection.setError("No se ha podido borrar " + ex);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
        return done;
    }

    public static void AIEmpate() {
        if (getUserByName("AI") == null) {
            signUpSpecial("AI");
        }

        if (getUserByName("Empate") == null) {
            signUpSpecial("Empate");
        }
    }
}
