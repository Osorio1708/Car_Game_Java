package sofkacar.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sofkacar.model.Player;
import sofkacar.model.Driver;

public class Connection {

    private String hostname;
    private String port;
    private String username;
    private String password;
    private String database;
    private Statement sm;
    private ResultSet rs;
    ArrayList<Player> players;
    private java.sql.Connection connection;

    public Connection() {
        this.hostname = "localhost";
        this.port = "3306";
        this.username = "root";
        this.password = "";
        this.database = "sofkacar";
    }

    public void connect() {
        String url = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database;
        try {
            this.connection = DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException ex) {
            this.connection = null;
        }
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            this.connection = null;
        }
        this.connection = null;
    }

    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet Query(String query) throws SQLException {
        if (!this.isConnected()) {
            this.connect();
        }
        Statement sm = this.connection.createStatement();
        return sm.executeQuery(query);
    }

    public int noQuery(String noQuery) throws SQLException {
        connect();
        Statement sm = this.connection.createStatement();
        return sm.executeUpdate(noQuery);
    }

    public boolean insertPlayer(String id, String name) {
        String comand = "INSERT INTO player VALUES ( '" + id + "', '" + name + "', 0, 0, 0)";
        try {
            noQuery(comand);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Player> queryToArrayPlayers() {
        String comand = "SELECT * FROM player";
        Player player;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            rs = Query(comand);
            while (rs.next()) {
                player = new Player(rs.getString("idPlayer"),
                        rs.getString("namePlayer"),
                        rs.getInt("numFirst"),
                        rs.getInt("numSec"),
                        rs.getInt("numTrd"));
                players.add(player);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return null;
        }
        disconnect();
        return players;
    }

    public ArrayList<Player> queryToArrayDrivers() {
        String comand = "SELECT * FROM player";
        Driver driver;
        this.players = new ArrayList<Player>();
        try {
            rs = Query(comand);
            while (rs.next()) {
                driver = new Driver(rs.getString("namePlayer"),
                        rs.getString("idPlayer"),
                        rs.getInt("numFirst"),
                        rs.getInt("numSec"),
                        rs.getInt("numTrd"));
                this.players.add(driver);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return null;
        }
        disconnect();
        return this.players;
    }

    public boolean updatePodium(ArrayList<Player> podium) {
        String comand = "INSERT INTO podium ( date, firstPlace, secondPlace, thirdPlace ) VALUES ( NOW(), '" + podium.get(0).getName() + "', '" + podium.get(1).getName() + "' ,'" + podium.get(2).getName() + "')";
        try {
            noQuery(comand);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    public boolean updateFirstPlace(Player player, int numFirst) {
        String comand = "UPDATE player SET numFirst=" + numFirst + " WHERE idPlayer='" + player.getId() + "'";
        try {
            noQuery(comand);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    public boolean updateSecondPlace(Player player, int numSec) {
        String comand = "UPDATE player SET numSec=" + numSec + " WHERE idPlayer='" + player.getId() + "'";
        try {
            noQuery(comand);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    public boolean updateThirdPlace(Player player, int numTrd) {
        String comand = "UPDATE player SET numTrd=" + numTrd + " WHERE idPlayer='" + player.getId() + "'";
        try {
            noQuery(comand);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    public ArrayList<String[]> getPodium() {
        String [] vecResult;
        ArrayList<String[]> aux = new ArrayList<String[]>();
        String comand = "SELECT * FROM podium WHERE 1 ORDER BY date DESC";
        try {
            this.rs = Query(comand);
            while(this.rs.next()){
                vecResult = new String[4];
                vecResult[0] = this.rs.getDate("date").toString();
                vecResult[1] = this.rs.getString("firstPlace");
                vecResult[2] = this.rs.getString("secondPlace");
                vecResult[3] = this.rs.getString("thirdPlace");
                aux.add(vecResult);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            disconnect();
        }
        return aux;
    }
}
