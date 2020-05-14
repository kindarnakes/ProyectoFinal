package Proyecto.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> _cartas;
    private ArrayList<Card> _sacadas;

    /**
     * Construye y aleatoriza una baraja.
     */
    public Deck() {
        this._cartas = new ArrayList<>();
        this._sacadas = new ArrayList<>();
        this.barajar();
    }

    /**
     * Inicializa y aleatoriza una baraja.
     */
    public final void barajar() {
        String[] palos = {"Espadas", "Oros", "Copas", "Bastos"};
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        int i = 0;
        _cartas = new ArrayList<>();
        _sacadas = new ArrayList<>();

        for (String p : palos) {
            i = 0;
            for (String n : numeros) {
                i++;
                _cartas.add(new Card(((i > 7) ? 0.5f : i), n + " de " + p));
            }
        }
        Collections.shuffle(_cartas);
    }

    /**
     * Saca la primera carta de la baraja, la carta se borra.
     *
     * @return Devuelve la carta en la parte superior de la baraja.
     */
    public Card sacarCarta() {
        Card sacada;
        try {
            sacada = _cartas.get(0);
        } catch (NullPointerException e) {
            sacada = new Card(0, "");
        }
        _cartas.remove(sacada);
        _sacadas.add(sacada);

        return sacada;
    }

    public ArrayList getCartas() {
        return _cartas;
    }

    public ArrayList getSacadas() {
        return _sacadas;
    }
}
