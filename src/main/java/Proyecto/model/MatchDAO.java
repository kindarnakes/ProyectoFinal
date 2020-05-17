package Proyecto.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    public static List<Match> getByUser(String username) {
        List<Match> matchs = new ArrayList<>();
        String sql = "SELECT * FROM play_with WHERE username1 = ? OR username2 = ?";

        try (Connection con = Data.getINSTANCE().get_conn();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, username);

            ResultSet rs = st.executeQuery();
            if (rs != null && rs.next()) {
                do {
                    Match aux = new Match(UserDAO.getUserByName(rs.getNString("username1")), UserDAO.getUserByName(rs.getNString("username2")),
                            UserDAO.getUserByName(rs.getNString("winner")),
                            rs.getDate("date").toLocalDate().atTime(rs.getTime("date").toLocalTime()), rs.getNString("game"));
                    matchs.add(aux);
                } while (rs.next());
                rs.close();
                matchs.sort(null);
            } else {
                DriverConnection.setError("No hay partidas registradas");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matchs;
    }


    public static boolean deleteMatch(Match m) {
        boolean done = false;
        String sql = "DELETE FROM play_with WHERE username1 = ? AND username2 = ? AND date = ?";
        if (m != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, m.get_user1().get_username());
                    st.setString(2, m.get_user2().get_username());
                    st.setTimestamp(3, Timestamp.valueOf(m.get_time()));
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


    public static boolean saveMatch(User u1, User u2, User winner, String game) {
        boolean save = false;

        String sql = "INSERT INTO play_with (username1, username2, winner, game, date) VALUES (?,?,?,?,?)";
        if (u1 != null && u2 != null && winner != null && game != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, u1.get_username());
                    st.setString(2, u2.get_username());
                    st.setString(3, winner.get_username());
                    st.setString(4, game);
                    st.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        save = true;
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
        return save;
    }

}
