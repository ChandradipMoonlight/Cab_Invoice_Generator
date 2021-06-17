package invoiceservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static invoiceservice.InvoiceGenerator.RideType.NORMAL;
import static invoiceservice.InvoiceGenerator.RideType.PREMIUM;


public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator = null;

    @BeforeEach
    void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @DisplayName("this is test case to check total fare when given Distance and time")
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time, NORMAL);
        Assertions.assertEquals(25, fare, 0.0);
    }

    @DisplayName("this is the test to check to return minimum fare when given less distance and time")
    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, NORMAL);
        Assertions.assertEquals(5, fare, 0.0);
    }

    @DisplayName("this is the test case to check total fare for multiple rides")
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(2.0, 5, NORMAL),
                new Ride(0.1, 1, NORMAL),
                new Ride(10, 6, NORMAL)
        };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 136.0);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }

    @DisplayName("this is test case to check Invoice Summary for given userId and rides")
    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() throws InvoiceException {
        String userId = "firstUser";
        Ride[] rides = {new Ride(2.0, 5, NORMAL),
                new Ride(0.1, 1, NORMAL)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenCategories_WhenRideList_ShouldReturnInvoiceSummary() throws InvoiceException {
        String userId = "firstUser";
        Ride[] rides = {new Ride(2.0, 5, PREMIUM),
                new Ride(0.1, 1, PREMIUM)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(2, 60.0);
        Assertions.assertEquals(summary, expectedSummary);
    }

}
