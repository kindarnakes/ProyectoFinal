package Proyecto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SieteYMedia {
    private Deck _deck;
    private HashMap<User, ArrayList<Card>> _users;

    public SieteYMedia(ArrayList<User> _userList) {
        this._users = new HashMap<>();
        for (User u : _userList) {
            ArrayList<Card> _cards = new ArrayList<>();
            this._users.put(u, _cards);
        }
        this._deck = new Deck();
    }

    public boolean turn(User u) {
        boolean done = false;

        if (this._users.containsKey(u)) {
            done = this._users.get(u).add(this._deck.sacarCarta());
        }

        return done;
    }

    public void reset() {
        this._deck.barajar();
        this._users.forEach((u, array) -> {
            array.clear();
        });
    }

    public double score(User u) {
        double score = 0;

        if (this._users.containsKey(u)) {
            ArrayList<Card> cards = this._users.get(u);
            Iterator<Card> it = cards.iterator();
            while (it.hasNext()) {
                score += it.next().getValor();
            }
        }

        return score;
    }

    public ArrayList<Card> playersCards(User u) {
        return this._users.get(u);
    }

    public void AI(User u) {

        while (this.score(u) < 6) {
            this.turn(u);
        }
    }

    public User winner() {
        User u = null;

        for (User us : _users.keySet()) {
            if (this.score(us) <= 7.5 && this.score(us) > this.score(u)) {
                u = us;
            } else if (this.score(us) == this.score(u)) {
                u = null;
            }
        }
        if (u == null) {
            u = new User();
            u.set_username("Empate");
        } else if (!u.get_username().equals("IA")) {
            int score = (int) score(u) * 10;
            if (score > 75) {
                score = 0;
            }
            Iterator<User> it = _users.keySet().iterator();
            MatchDAO.saveMatch(it.next(), it.next(), u, "7yMedia");
            ScoreDAO.save(u.get_username(), (int) (score / playersCards(u).size()) * 10, "7yMedia");
        }
        return u;
    }
}
