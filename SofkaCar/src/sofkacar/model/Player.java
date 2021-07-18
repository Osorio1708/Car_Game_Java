
package sofkacar.model;
import sofkacar.controller.Connection;

public class Player {
    String name;
    String id;
    int firstPosition;
    int secondPosition;
    int thirdPosition;
    Connection con;
    
    public Player(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Player(String name, String id, int firstPosition, int secondPosition, int thirdPosition) {
        this.name = name;
        this.id = id;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.thirdPosition = thirdPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }

    public int getThirdPosition() {
        return thirdPosition;
    }

    public void setThirdPosition(int thirdPosition) {
        this.thirdPosition = thirdPosition;
    }
    
    public boolean insertUser(){
        con = new Connection();
        return con.insertPlayer(this.id,this.name);
    }
    
}
