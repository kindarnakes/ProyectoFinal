package Proyecto.model;

import java.sql.Blob;
import java.time.LocalDate;

public class User {
    private String _username;
    private String _password;
    private String _email;
    private LocalDate _born;
    private Blob _img;

    public User() {
        this._username = "";
        this._password = "";
    }

    public User(String _username, String _password, String _email, LocalDate _born, Blob _img) {
        this._username = _username;
        this._password = _password;
        this._email = _email;
        this._born = _born;
        this._img = _img;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public LocalDate get_born() {
        return _born;
    }

    public void set_born(LocalDate _born) {
        this._born = _born;
    }

    public Blob get_img() {
        return _img;
    }

    public void set_img(Blob _img) {
        this._img = _img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return _username != null ? _username.equals(user._username) : user._username == null;
    }

    @Override
    public int hashCode() {
        return _username != null ? _username.hashCode() : 0;
    }
}
