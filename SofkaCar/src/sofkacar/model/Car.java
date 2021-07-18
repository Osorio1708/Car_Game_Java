package sofkacar.model;

public class Car {

    private int totalRoute;
    private int actualPosition;
    private int laneNumber;

    public Car(int totalRoute, int laneNumber) {
        this.laneNumber = laneNumber;
        this.totalRoute = totalRoute;
        this.actualPosition = 0;
    }

    public Car(int totalRoute, int actualPosition, int laneNumber) {
        this.totalRoute = totalRoute;
        this.actualPosition = actualPosition;
        this.laneNumber = laneNumber;
    }

    public int getTotalRoute() {
        return totalRoute;
    }

    public void setTotalRoute(int totalRoute) {
        this.totalRoute = totalRoute;
    }

    public int getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(int actualPosition) {
        this.actualPosition = actualPosition;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

}
