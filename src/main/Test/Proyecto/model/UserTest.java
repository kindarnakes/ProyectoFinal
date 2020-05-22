package Proyecto.model;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    static ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");

    @Test
    public void testCreateUser() {
        LocalDate date = LocalDate.now();
        User u = new User("prueba", "prueba", "", date, null);

        assertEquals(u.get_username(), "prueba");
        assertEquals(u.get_password(), "prueba");
        assertEquals(u.get_email(), "");
        assertEquals(u.get_born(), date);
        assertEquals(u.get_img(), null);

    }

    @Test
    public void testSaveUser() {
        LocalDate date = LocalDate.now();
        User u = new User("prueba", "prueba", "", date, null);

        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            assertTrue(UserDAO.signUp(u.get_username(), u.get_password(), u.get_email(), u.get_born(), null));
        } catch (SQLException ex) {
            assert false;
        }


    }

    @Test
    public void testSearchUser() {
        ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");
        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            User u = new User("pruebaSearch", "prueba", "", null, null);
            assertTrue(UserDAO.signUp(u.get_username(), u.get_password(), u.get_email(), u.get_born(), null));
            assertEquals(UserDAO.getUserByName(u.get_username()), u);
            assertTrue(UserDAO.deleteUser("pruebaSearch"));
        } catch (SQLException ex) {
            assert false;
        }

    }

    @Test
    public void testUpdateUser() {
        ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");
        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            User u = new User("pruebaUpdate", "prueba", "", null, null);
            assertTrue(UserDAO.signUp(u.get_username(), u.get_password(), u.get_email(), u.get_born(), null));
            assertTrue(UserDAO.userUpdate("pruebaUpdated", u.get_password(), u.get_email(), u.get_born(), null, u.get_username()));
            u.set_username("pruebaUpdated");
            assertEquals(UserDAO.getUserByName(u.get_username()), u);
            assertTrue(UserDAO.deleteUser("pruebaUpdated"));
        } catch (SQLException ex) {
            assert false;
        }


    }


    @Test
    public void testDeleteUser() {
        ConnectionData connectionData = new ConnectionData("localhost", "juegos", "root", "10junio");

        Data.getINSTANCE().set_conn(connectionData);
        try (Connection con = Data.getINSTANCE().get_conn();) {
            assertTrue(UserDAO.deleteUser("prueba"));
        } catch (SQLException ex) {
            assert false;
        }
    }


}