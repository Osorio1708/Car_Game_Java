package sofkacar.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegisterPlayerModel {

    private Player player;
    private Query qr;

    public RegisterPlayerModel() {
        this.qr = new Query();
    }

    public DefaultTableModel updateTableRegister() {
        ArrayList<Player> aPlayers;
        Object mPlayers[][];
        String[] nameColumns = {"Id Player", "Name Player", "First Position", "Second Position", "Third Position"};
        aPlayers = qr.filTablePlayers();
        mPlayers = new Object[aPlayers.size()][5];
        for (int i = 0; i < aPlayers.size(); i++) {
            mPlayers[i][0] = aPlayers.get(i).getId();
            mPlayers[i][1] = aPlayers.get(i).getName();
            mPlayers[i][2] = aPlayers.get(i).getFirstPosition();
            mPlayers[i][3] = aPlayers.get(i).getSecondPosition();
            mPlayers[i][4] = aPlayers.get(i).getThirdPosition();
        }
        return new DefaultTableModel(mPlayers, nameColumns);
    }

    public void registerPlayer(String id, String name) {
        try {
            Integer.parseInt(id);
            if (id.length() == 4 && !name.equalsIgnoreCase("")) {
                player = new Player(name, id);
                if (this.qr.insertUser(player)) {
                    message("Successfully join Player");
                } else {
                    message("Player not saved");
                }
            } else {
                message("Enter a 4-digit number");
                message("Enter a name that has at least 1 character");
            }
        } catch (Exception e) {
            message("Only numbers can be entered in the Id field");
        }
    }

    public String inputMessage(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    public void message(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

}
