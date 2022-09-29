package model.ticket;

import model.customer.Customer;

/**
 * Represents a ticket.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class Ticket implements Comparable<Ticket> {
    private Customer customer;
    private final int billNumber;
    private static int nextNum = 1;
    private int trainNumber;

    /**
     * Creates a new ticket.
     * 
     * @param trainNumber
     * @param customer
     */
    public Ticket(int trainNumber, Customer customer) {
        this.customer = customer;
        this.billNumber = nextNum++;
        this.trainNumber = trainNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String toString() {
        return customer.getCustomerNumber() + ";" + this.getTrainNumber() + ";" + this.getBillNumber();

    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public int compareTo(Ticket t) {
        if (this.billNumber < t.getBillNumber()) {
            return 1;
        } else if (this.billNumber > t.getBillNumber()) {
            return -1;
        }

        return 0;
    }

}
