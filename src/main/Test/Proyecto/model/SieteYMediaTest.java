package Proyecto.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SieteYMediaTest {

    @Test
    void score() {
        User u1 = new User();
        u1.set_username("u1");

        User u2 = new User();
        u1.set_username("u2");

        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        SieteYMedia juego = new SieteYMedia(users);

        juego.turn(u1);
        juego.turn(u2);

        double u1s = juego.score(u1);
        double u2s = juego.score(u2);

        Card c1 = juego.playersCards(u1).get(0);
        Card c2 = juego.playersCards(u2).get(0);

        assertEquals(u1s, c1.getValor(), 0.000005f);
        assertEquals(u2s, c2.getValor(), 0.000005f);
    }

    @Test
    void winner() {

        User u1 = new User();
        u1.set_username("u1");

        User u2 = new User();
        u1.set_username("u2");

        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        SieteYMedia juego = new SieteYMedia(users);

        juego.turn(u1);
        juego.turn(u2);

        double u1s = juego.score(u1);
        double u2s = juego.score(u2);

        User w = juego.winner();
        if (u1s > u2s) {
            assertEquals(w, u1);
        } else if (u1s < u2s) {
            assertEquals(w, u2);
        } else if (u1s == u2s) {
            User empate = new User();
            empate.set_username("Empate");
            assertEquals(w, empate);
        }


    }
}