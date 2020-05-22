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

    /**
     * Elecuta el turno de un jugador dado en el que saca una carta mas
     * @param u jugador/usuario que ejecuta el turno
     * @return devuelve si el tuno ha podido ejecutarse o no
     */
    public boolean turn(User u) {
        boolean done = false;

        if (this._users.containsKey(u)) {
            done = this._users.get(u).add(this._deck.sacarCarta());
        }

        return done;
    }

    /**
     * Borra los cartas sacadas y vuelve a barajar.
     */
    public void reset() {
        this._deck.barajar();
        this._users.forEach((u, array) -> {
            array.clear();
        });
    }

    /**
     * Devuelve la puntuacion de un usuario/jugador
     * @param u usuario a calcular la puntuacion
     * @return devuelve la puntuacion del usuario
     */
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

    /**
     * Devuelve la cartas de un usuario
     * @param u usuario del que queremos las cartas
     * @return cartas del usuario
     */
    public ArrayList<Card> playersCards(User u) {
        return this._users.get(u);
    }

    /**
     * Ejecuta la IA del juego
     * @param u usuario que representa la IA
     */
    public void AI(User u) {

        while (this.score(u) < 6) {
            this.turn(u);
        }
    }

    /**
     * Calcula que usuario ha ganado la partida, y la da por completada almacenando los datos correspondientes a la partida en la BD
     * @return devuelve el usuario ganador
     */
    public User winner() {
        User u = null;
        double uscore = 0;
        double usscore = 0;
        boolean empate = false;

        for (User us : _users.keySet()) {
            if(!empate){
                uscore = this.score(u);
            }
            usscore = this.score(us);
            //si algun usuario se pasa, la puntuacion es -1
            if(uscore > 7.5){
                uscore = -1;
            }
            if(usscore > 7.5){
                uscore = -1;
            }
            if (usscore <= 7.5 && usscore > uscore) {
                u = us;
                empate = false;
            } else if (usscore == uscore) { //si dos usuarios tienen la misma puntuacion
                empate = true;
            }
        }
        if (empate || u == null) { //si se ha empatado o todos se han pasado
            u = new User();
            u.set_username("Empate");
        }

            Iterator<User> it = _users.keySet().iterator();
            MatchDAO.saveMatch(it.next(), it.next(), u, "7yMedia");
            for(User user: _users.keySet()){
                if(Data.getINSTANCE().get_logged().equals(user)){
                    double score = (score(user) * 10);
                    if (score > 75) {
                        score = 0;
                    }else if(score == 75){
                        score *= 2;
                    }
                    ScoreDAO.save(user.get_username(), (int)((score / playersCards(user).size()) * 10), "7yMedia");

            }

        }
        return u;
    }
}
