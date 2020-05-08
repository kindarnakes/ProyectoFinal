package Proyecto.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SieteYMedia {
    private Deck _deck;
    private HashMap<User, ArrayList<Card>> _users;

    public SieteYMedia(ArrayList<User> _userList) {
        this._users = new HashMap<>();
        for(User u : _userList){
            ArrayList<Card> _cards = new ArrayList<>();
            this._users.put(u, _cards);
        }
        this._deck = new Deck();
    }

    public boolean turn(User u){
        boolean done = false;

        if(this._users.containsKey(u)){
            done = this._users.get(u).add(this._deck.sacarCarta());
        }

        return done;
    }
}
