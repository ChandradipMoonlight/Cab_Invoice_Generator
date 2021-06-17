package invoiceservice;

public class InvoiceSummary {

    /**
     * declaration instance of variables.
     */

    private final int numOfRides;
    private final double totalFare;
    private final double avgFare;

    /**
     * this is constructor of the class InvoiceSummary to initialize the parameters.
     * @param numOfRides;
     * @param totalFare;
     */
    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.avgFare = this.totalFare/this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceSummary that)) return false;
        return numOfRides == that.numOfRides && Double.compare(that.totalFare, totalFare) == 0 && Double.compare(that.avgFare, avgFare) == 0;
    }
}
