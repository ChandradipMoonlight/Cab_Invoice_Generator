package invoiceservice;

public class InvoiceGenerator {

    /**
     * declaration of the constants
     */
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final int MINIMUM_FARE = 5;

    private RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    /**
     * this is the method to calculate the total fare for single ride when given distance and time;
     * @param distance;
     * @param time;
     * @return double;
     */
    public double calculateFare(double distance, int time) {
        double totalFare = distance*MINIMUM_COST_PER_KILOMETER+time*COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    /**
     * this is the method to calculate total fare for multiple rides.
     * @param rides ;
     * @return double;
     */
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    /**
     * to add ride by user id
     * @param userId;
     * @param rides;
     */
    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    /**
     * Method to get invoice summary by user id
     * @param userId;
     * @return Invoice summary;
     */
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}

