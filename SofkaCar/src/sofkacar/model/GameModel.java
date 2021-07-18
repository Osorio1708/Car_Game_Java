package sofkacar.model;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GameModel {

    private ArrayList<Player> driversInGame;
    private ArrayList<Player> podium;
    private Query qr;
    private int numPlayers;
    private int distance;
    private String vCodP[];

    public GameModel() {
        this.qr = new Query();
        this.podium = new ArrayList<Player>();
    }

    public void initGame() {
        this.numPlayers = 0;
        this.distance = 0;
        String codPlayer;
        try {
            message("Enter a maximum number of 15 Driverss");
            do {
                this.numPlayers = Integer.parseInt(inputMessage("Enter the number of Drivers"));
                this.distance = Integer.parseInt(inputMessage("Enter the distance in Kilometers of the track"));
                if (!this.qr.quantityValidate(this.numPlayers)) {
                    message("There are not enough players in the database");
                }
                if (this.numPlayers > 15 || this.numPlayers < 3) {
                    message("The number must be greater than 3 and less than 15");
                }
                if (this.distance <= 0 || this.distance < 6) {
                    message("The course of the track must be positive and must be at least 6 kilometers long.");
                }
            } while (this.numPlayers > 15 || this.numPlayers < 3 || !this.qr.quantityValidate(this.numPlayers) || this.distance <= 0 || this.distance < 6);
            this.vCodP = new String[this.numPlayers];
            for (int i = 0; i < this.numPlayers; i++) {
                do {
                    codPlayer = inputMessage("Enter the code of the player number " + (i + 1) + ":");
                    if (!this.qr.playerExists(codPlayer)) {
                        message("The id was not found among the available players");
                    }
                    if (noRepeat(codPlayer)) {
                        message("The user has already join");
                    }
                } while (!this.qr.playerExists(codPlayer) || noRepeat(codPlayer));
                this.vCodP[i] = codPlayer;
            }
            this.driversInGame = this.qr.playersInGame(vCodP, distance);
            updateTableGame();

        } catch (Exception e) {
            message(e.getMessage());
        }
    }

    public void play() {
        int movDistance = 0;
        int actualPosition = 0;
        for (int i = 0; i < this.driversInGame.size(); i++) {
            movDistance = randomMethod();
            actualPosition = ((Driver) this.driversInGame.get(i)).getPlayerCar().getActualPosition() + movDistance;
            if (actualPosition < (distance * 1000)) {
                ((Driver) this.driversInGame.get(i)).getPlayerCar().setActualPosition(actualPosition);
            } else if (actualPosition >= (distance * 1000)) {
                this.podium.add(this.driversInGame.get(i));
                this.driversInGame.remove(i);
            }
        }

    }

    public DefaultTableModel updateTableGame() {
        Object mPlayers[][];
        Query td = new Query();
        String[] nameColumns = {"Name Player", "Lane", "Actual Position", "Metros Finish"};
        mPlayers = new Object[this.driversInGame.size()][4];
        for (int i = 0; i < this.driversInGame.size(); i++) {
            mPlayers[i][0] = this.driversInGame.get(i).getName();
            mPlayers[i][1] = ((Driver) this.driversInGame.get(i)).getPlayerCar().getLaneNumber();
            mPlayers[i][2] = ((Driver) this.driversInGame.get(i)).getPlayerCar().getActualPosition();
            mPlayers[i][3] = ((Driver) this.driversInGame.get(i)).getPlayerCar().getTotalRoute() - ((Driver) this.driversInGame.get(i)).getPlayerCar().getActualPosition();
        }
        return new DefaultTableModel(mPlayers, nameColumns);
    }

    public DefaultTableModel updateTablePodium() {
        Object mPlayers[][];
        String[] nameColumns = {"Player Name", "Posituion",};
        mPlayers = new Object[this.podium.size()][2];
        for (int i = 0; i < this.podium.size(); i++) {
            mPlayers[i][0] = this.podium.get(i).getName();
            mPlayers[i][1] = i + 1;
        }
        return new DefaultTableModel(mPlayers, nameColumns);
    }

    public boolean savePodium() {
        return this.qr.savePodium(this.podium);
    }

    public void message(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public int randomMethod() {
        Random r = new Random();
        int value = r.nextInt(6) + 1;
        return value * 100;
    }

    public boolean noRepeat(String value) {
        for (int i = 0; i < this.vCodP.length; i++) {
            if (value.equalsIgnoreCase(this.vCodP[i])) {
                return true;
            }
        }
        return false;
    }
    
    public void podiumPresentation(){
        message(((Driver)podium.get(0)).presentation() + "1");
        message(((Driver)podium.get(1)).presentation() + "2");
        message(((Driver)podium.get(2)).presentation() + "3");
    }

    public boolean gameOver() {
        return this.podium.size() == this.numPlayers;
    }

    public String inputMessage(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

}
