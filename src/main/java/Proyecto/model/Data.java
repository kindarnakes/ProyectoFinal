package Proyecto.model;


import java.sql.Connection;
import java.util.ArrayList;

public class Data {
    private static Data INSTANCE = new Data();
    private User _logged;
    private ArrayList<Score> _scores;
    private ArrayList<Match> _historic;
    private ConnectionData _conn;


    public Data() {
        this.INSTANCE = this;
        this._scores = new ArrayList<>();
        this._historic = new ArrayList<>();
        this._conn = new ConnectionData();
    }

    public static Data getINSTANCE() {
        return INSTANCE;
    }

    public User get_logged() {
        return _logged;
    }

    public void set_logged(User u) {
        this._logged = u;
    }

    public ArrayList<Score> get_scores() {
        return _scores;
    }

    public ArrayList<Match> get_historic() {
        return _historic;
    }

    public Connection get_conn() {
        return DriverConnection.Connection(this._conn.getIp(), this._conn.getBd(), this._conn.getUser(), this._conn.getPass());
    }

    public void set_conn(ConnectionData _conn) {
        this._conn = _conn;
    }
}
