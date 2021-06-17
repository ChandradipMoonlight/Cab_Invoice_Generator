package invoiceservice;

import java.util.Arrays;

public class InvoiceGenerator {

    public enum RideType {
        NORMAL, PREMIUM
    }

    /**
     * declaration of the constants
     */
    private static final double MIN_COST_PER_KM_NORMAL_RIDE = 10;
    private static final int COST_PER_TIME_NORMAL_RIDE = 1;
    private static final double MIN_FARE_NORMAL_RIDE = 5;

    private static final double MIN_COST_PER_KM_PREMIUM_RIDE = 15;
    private static final int COST_PER_TIME_PREMIUM_RIDE = 2;
    private static final double MIN_FARE_PREMIUM_RIDE = 20;


    private final RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    /**
     * Method to calculate fare for single ride.
     * @param distance;
     * @param time;
     * @param rideType;
     * @return fare;
     */
    public double calculateFare(double distance, int time, RideType rideType) {
        return this.calculateBasedOnRideType(distance, time, rideType);
    }

    /**
     * Method to calculate total fare for multiple rides.
     * @param rides;
     * @return invoice summary;
     */
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides)
                .mapToDouble(ride -> this.calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType()))
                .sum();
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

    /**
     * Method to choose ride and return fare
     * @param distance;
     * @param time;
     * @param rideType;
     * @return total fare;
     */
    public double calculateBasedOnRideType(double distance, int time, RideType rideType) {
        return switch (rideType) {
            case PREMIUM -> Math.max(distance * MIN_COST_PER_KM_PREMIUM_RIDE + time * COST_PER_TIME_PREMIUM_RIDE,
                    MIN_FARE_PREMIUM_RIDE);
            case NORMAL -> Math.max(distance * MIN_COST_PER_KM_NORMAL_RIDE + time * COST_PER_TIME_NORMAL_RIDE,
                    MIN_FARE_NORMAL_RIDE);
        };
    }
}

