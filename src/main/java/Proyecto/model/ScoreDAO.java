package Proyecto.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {


    public static List<Score> getScoreTableByGame(String game) {
        List<Score> scores = new ArrayList<>();

        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from scores WHERE game = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, game);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    do {
                        Score aux = new Score(rs.getString("username"), rs.getInt("score"), game);
                        scores.add(aux);
                    } while (rs.next());
                    rs.close();
                    scores.sort(null);
                } else {
                    DriverConnection.setError("No hay puntuaciones registradas");
                }
            } catch (SQLException ex) {
                DriverConnection.setError("No se ha podido conectar " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return scores;
    }

    public static Score getScoreTableByUserAndGame(String username, String game) {
        Score aux = null;
        Connection conn = Data.getINSTANCE().get_conn();
        if (conn != null) {
            String sql = "SELECT * from scores WHERE username = ? AND game = ?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, username);
                st.setString(2, game);
                ResultSet rs = st.executeQuery();
                if (rs != null && rs.next()) {
                    aux = new Score(rs.getString("username"), rs.getInt("score"), game);
                    rs.close();
                } else {
                    DriverConnection.setError("No hay puntuaciones registradas");
                }
            } catch (SQLException ex) {
                DriverConnection.setError("No se ha podido conectar " + ex);
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return aux;
    }

    public static boolean deleteScoreByUsername(String username) {
        boolean delete = false;

        String sql = "DELETE FROM scores WHERE username = ?";
        if (username != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, username);
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        delete = true;
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
        return delete;
    }

    public static boolean deleteScoreGameByUsername(String username, String game) {
        boolean delete = false;

        String sql = "DELETE FROM scores WHERE username = ? AND game = ?";
        if (username != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, username);
                    st.setString(2, game);
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        delete = true;
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
        return delete;
    }

    public static boolean save(String user, int score, String game) {
        UserDAO.AIEmpate();
        Score s = getScoreTableByUserAndGame(user, game);
        boolean save = false;
        String sql = null;
        if (s != null && s.get_score() < score) {
            sql = "UPDATE scores SET score = ? WHERE username = ? AND game = ?";
        }
        if (s == null) {
            sql = "INSERT INTO scores(score, username, game) VALUES (?,?,?)";
        }

        if (sql != null) {
            Connection conn = Data.getINSTANCE().get_conn();
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, score);
                    st.setString(2, user);
                    st.setString(3, game);
                    Integer rs = st.executeUpdate();
                    if (rs != null && rs == 1) {
                        save = true;
                    }
                } catch (SQLException ex) {
                    DriverConnection.setError("Error " + ex);
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
