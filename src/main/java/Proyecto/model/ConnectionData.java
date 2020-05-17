package Proyecto.model;

public class ConnectionData {

    private String ip;
    private String bd;
    private String user;
    private String pass;

    public ConnectionData() {
        this.ip = "localhost:3306";
        this.bd = "juegos";
        this.user = "root";
        this.pass = "10junio";
    }

    public ConnectionData(String ip, String bd, String user, String pass) {
        this.ip = ip;
        this.bd = bd;
        this.user = user;
        this.pass = pass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
