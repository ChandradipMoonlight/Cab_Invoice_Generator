package invoiceservice;

public class InvoiceGenerator {
    /**
     * declaration of the constants
     */
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final int MIN_FARE = 5;

    /**
     * this is method to calculate fare of the ride when distance and time.
     * @param distance;
     * @param time;
     * @return double;
     */
    public double calculateFare(double distance, int time) {
        double totalFare = distance*MINIMUM_COST_PER_KILOMETER+time*COST_PER_TIME;
        if (totalFare< MIN_FARE)
            return MIN_FARE;
            return totalFare;
    }
}

