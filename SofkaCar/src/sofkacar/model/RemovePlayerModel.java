package sofkacar.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RemovePlayerModel {

    private Query qr;

    public RemovePlayerModel() {
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

    public void removePlayerDB(String id) {

        try {
            Integer.parseInt(id);
            if (id.length() == 4) {
                if (this.qr.deletePlayer(id)) {
                    message("player successfully eliminated");
                } else {
                    message("Player not eliminated");
                }
            } else {
                message("Enter a 4-digit numeric value");
            }
        } catch (Exception e) {
            message("Enter a 4-digit numeric value");
        }
    }

    private void message(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

}
