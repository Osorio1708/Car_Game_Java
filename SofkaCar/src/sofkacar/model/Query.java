
package sofkacar.model;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sofkacar.controller.Connection;


public class Query {
    ArrayList<Player> aPlayer;
    Object[] vecCar;
    Connection con;
    boolean flag;
    public Query(){
        this.con = new Connection();
    }
    
    public ArrayList<Player> filTablePlayers(){
        return this.con.queryToArrayPlayers();
    }
    
     public ArrayList<Player> getDriversToGame(){
        return this.con.queryToArrayDrivers();
    }
    
   
    public void createVeCar(int quantity, int distance){
        for( int i = 0; i < quantity; i++ ){
            this.vecCar[i] = new Car(distance,i);
        }
    }
    
      public int getQuantityPlayers(){
        this.aPlayer = this.con.queryToArrayPlayers();
        return this.aPlayer.size();
    }
    
    public boolean quantityValidate(int value){
        int quantity = getQuantityPlayers();
        if( quantity <  value ){
            return false;
        }else if(value < 0){
            return false;
        }
        return true;
    }
    
    public boolean playerExists(String Id){
        this.aPlayer = getDriversToGame();
        
        for( int i = 0 ; i < this.aPlayer.size();i++){
            if(this.aPlayer.get(i).getId().equalsIgnoreCase(Id)){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Player> playersInGame(String[] vPlayers, int distance){
        ArrayList<Player> aux = new ArrayList<Player>();
        Driver driver;
        this.aPlayer = getDriversToGame();
        for(int i = 0 ; i < vPlayers.length; i++){
            for(int j = 0 ; j < this.aPlayer.size(); j++){
                if(vPlayers[i].equalsIgnoreCase(this.aPlayer.get(j).getId())){
                    driver = new Driver(this.aPlayer.get(j).getName(),this.aPlayer.get(j).getId(), new Car((distance*1000),(i+1)));
                    aux.add(driver);
                }
            }
        }
        return aux;
    }
    
    public boolean savePodium( ArrayList<Player> podium ){
        this.aPlayer = getDriversToGame();
        for( int i = 0 ; i < this.aPlayer.size() ; i++ ){
            if(this.aPlayer.get(i).getId().equalsIgnoreCase(podium.get(0).getId())){
                this. flag = this.con.updateFirstPlace(podium.get(0), (this.aPlayer.get(i).getFirstPosition()+1));
            }else if(this.aPlayer.get(i).getId().equalsIgnoreCase(podium.get(1).getId())){
                this. flag = this.con.updateSecondPlace(podium.get(1), (this.aPlayer.get(i).getSecondPosition()+1));
            }else if(this.aPlayer.get(i).getId().equalsIgnoreCase(podium.get(2).getId())){
                this. flag = this.con.updateThirdPlace(podium.get(2), (this.aPlayer.get(i).getThirdPosition()+1));
            }
        }
        this. flag = this.con.updatePodium(podium);
        return this.flag;
    }
    
    public ArrayList<String[]> getPodium(){
        return this.con.getPodium();
    }
    
}
