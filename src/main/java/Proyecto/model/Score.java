package Proyecto.model;

public class Score implements Comparable<Score> {
    private String _user;
    private Integer _score;
    private String _game;

    public Score(String _user, Integer _score, String _game) {
        this._user = _user;
        this._score = _score;
        this._game = _game;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public Integer get_score() {
        return _score;
    }

    public void set_score(Integer _score) {
        this._score = _score;
    }

    public String get_game() {
        return _game;
    }

    public void set_game(String _game) {
        this._game = _game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score = (Score) o;

        if (_user != null ? !_user.equals(score._user) : score._user != null) return false;
        return _game != null ? _game.equals(score._game) : score._game == null;
    }

    @Override
    public int hashCode() {
        int result = _user != null ? _user.hashCode() : 0;
        result = 31 * result + (_game != null ? _game.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(o._score, this._score);
    }
}
