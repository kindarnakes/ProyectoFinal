package Proyecto.model;

import java.time.LocalDateTime;

public class Match {
    private User _user1;
    private User _user2;
    private User _winner;
    private LocalDateTime _time;
    private String _game;

    public Match(User _user1, User _user2, User _winner, LocalDateTime _time, String _game) {
        this._user1 = _user1;
        this._user2 = _user2;
        this._winner = _winner;
        this._time = _time;
        this._game = _game;
    }


    public User get_user1() {
        return _user1;
    }

    public void set_user1(User _user1) {
        this._user1 = _user1;
    }

    public User get_user2() {
        return _user2;
    }

    public void set_user2(User _user2) {
        this._user2 = _user2;
    }

    public User get_winner() {
        return _winner;
    }

    public void set_winner(User _winner) {
        this._winner = _winner;
    }

    public LocalDateTime get_time() {
        return _time;
    }

    public void set_time(LocalDateTime _time) {
        this._time = _time;
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
        if (!(o instanceof Match)) return false;

        Match match = (Match) o;

        if (_user1 != null ? !_user1.equals(match._user1) : match._user1 != null) return false;
        if (_user2 != null ? !_user2.equals(match._user2) : match._user2 != null) return false;
        if (_winner != null ? !_winner.equals(match._winner) : match._winner != null) return false;
        if (_time != null ? !_time.equals(match._time) : match._time != null) return false;
        return _game != null ? _game.equals(match._game) : match._game == null;
    }

    @Override
    public int hashCode() {
        int result = _user1 != null ? _user1.hashCode() : 0;
        result = 31 * result + (_user2 != null ? _user2.hashCode() : 0);
        result = 31 * result + (_winner != null ? _winner.hashCode() : 0);
        result = 31 * result + (_time != null ? _time.hashCode() : 0);
        result = 31 * result + (_game != null ? _game.hashCode() : 0);
        return result;
    }
}
