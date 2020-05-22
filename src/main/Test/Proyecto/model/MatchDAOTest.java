package Proyecto.model;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchDAOTest {

    static ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");

    @Test
    void MatchTest() {


        LocalDate date = LocalDate.now();
        User u = new User("prueba", "prueba", "", date, null);

        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            assertTrue(UserDAO.signUp(u.get_username(), u.get_password(), u.get_email(), u.get_born(), null));
            assertTrue(MatchDAO.saveMatch(u, u, u, "prueba"));
            List<Match> m = MatchDAO.getByUser(u.get_username());
            assertNotEquals(null, m);
            m.forEach(match -> {
                assertTrue(MatchDAO.deleteMatch(match));
            });
            assertTrue(UserDAO.deleteUser(u.get_username()));
        } catch (SQLException ex) {
            assert false;
        }

    }

}