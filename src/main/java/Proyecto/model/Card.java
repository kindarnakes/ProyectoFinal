package Proyecto.model;

public class Card {
    private float _valor;
    private String _name;

    public Card(float _valor, String _name) {
        this._valor = _valor;
        this._name = _name;
    }

    public float getValor() {
        return _valor;
    }

    public void setValor(float _valor) {
        this._valor = _valor;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    @Override
    public String toString() {
        return "Valor= " + _valor + ", Nombre= " + _name;
    }
}
