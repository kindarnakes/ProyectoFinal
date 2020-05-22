package Proyecto.model;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ScoreDAOTest {

    static ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");

    @Test
    void ScoreTest() {

        LocalDate date = LocalDate.now();
        User u = new User("prueba", "prueba", "", date, null);

        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            assertTrue(UserDAO.signUp(u.get_username(), u.get_password(), u.get_email(), u.get_born(), null));
            assertTrue(ScoreDAO.save(u.get_username(), 0, "prueba"));
            Score s = ScoreDAO.getScoreTableByUserAndGame(u.get_username(), "prueba");
            assertNotEquals(null, s);
            assertTrue(ScoreDAO.deleteScoreByUsername(u.get_username()));
            assertTrue(UserDAO.deleteUser(u.get_username()));
        } catch (SQLException ex) {
            assert false;
        }
    }
}