package Proyecto.model;


import java.sql.Connection;

public class Data {
    private static Data INSTANCE = new Data();
    private User _logged;
    private ConnectionData _conn;


    public Data() {
        this.INSTANCE = this;
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

    public Connection get_conn() {
        return DriverConnection.Connection(this._conn.getIp(), this._conn.getBd(), this._conn.getUser(), this._conn.getPass());
    }

    public void set_conn(ConnectionData _conn) {
        this._conn = _conn;
    }

    public boolean login(String username, String pass) {
        boolean login = false;
        if (username != null && pass != null && (username.length() > 0 && pass.length() > 0)) {
            User u = UserDAO.getUserByNameAndPass(username, pass);
            if (u != null && (u.get_username().equals(username))) {
                login = true;
                this._logged = u;
            }
        }
        return login;
    }
}
