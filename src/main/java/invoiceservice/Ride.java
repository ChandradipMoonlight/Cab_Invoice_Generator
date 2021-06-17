package invoiceservice;

public class Ride {

    /**
     * declaration of instance of the variables
     */

    private int time;
    private double distance;

    /**
     * this is the constructor of class Ride to initialize the parameter;
     * @param distance;
     * @param time;
     */
    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    /**
     * this are the getter and setter methods to get time and distance.
     */
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
