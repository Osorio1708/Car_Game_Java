package sofkacar.model;

public class Driver extends Player {

    private Car playerCar;

    public Driver(String name, String id, int firstPosition, int secondPosition, int thirdPosition) {
        super(name, id, firstPosition, secondPosition, thirdPosition);
    }

    public Driver(String name, String id, Car playerCar) {
        super(name, id);
        this.playerCar = playerCar;
    }

    public Car getPlayerCar() {
        return playerCar;
    }

    public void setPlayerCar(Car playerCar) {
        this.playerCar = playerCar;
    }
    
    @Override
    public String presentation(){
        return "Name: " + this.name + "\n" +
               "Lane: " + this.playerCar.getLaneNumber() + "\n" +
               "Position: ";      
    }
}
