package sofkacar.model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PodiumModel {

    private ArrayList<String[]> hPodium;
    private Query qr;

    public PodiumModel() {
        qr = new Query();
    }

    public DefaultTableModel updateTablePodium() {
        Object mPlayers[][];
        setHPodium();
        String[] nameColumns = {"Dater", "First Place", "Second Place", "Thrid Place"};
        mPlayers = new Object[this.hPodium.size()][4];
        for (int i = 0; i < this.hPodium.size(); i++) {
            mPlayers[i][0] = this.hPodium.get(i)[0];
            mPlayers[i][1] = this.hPodium.get(i)[1];
            mPlayers[i][2] = this.hPodium.get(i)[2];
            mPlayers[i][3] = this.hPodium.get(i)[3];
        }
        return new DefaultTableModel(mPlayers, nameColumns);
    }

    public void setHPodium() {
        this.hPodium = this.qr.getPodium();
    }
}
