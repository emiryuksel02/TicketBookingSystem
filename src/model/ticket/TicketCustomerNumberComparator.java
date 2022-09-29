package model.ticket;

/**
 * Compares bookings based customer number.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class TicketCustomerNumberComparator implements Comparable<TicketCustomerNumberComparator> {
    private final int customerNumber;
    private final int trainNumber;
    private final int billNumber;
    private String formattedTrainNumber;

    /**
     * Creates a new comparator.
     * 
     * @param customerNumber
     * @param trainNumber
     * @param billNumber
     */
    public TicketCustomerNumberComparator(int customerNumber, int trainNumber, int billNumber) {
        this.customerNumber = customerNumber;
        this.trainNumber = trainNumber;
        this.billNumber = billNumber;

    }

    @Override
    public int compareTo(TicketCustomerNumberComparator t) {
        if (this.customerNumber < t.getCustomerNumber()) {
            return -1;
        }

        else if (this.customerNumber > t.getCustomerNumber()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        this.formattedTrainNumber = String.format("%03d", this.trainNumber);
        return this.customerNumber + ";" + this.formattedTrainNumber + ";" + this.billNumber;

    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public String getFormattedTrainNumber() {
        return formattedTrainNumber;
    }

    public void setFormattedTrainNumber(String formattedTrainNumber) {
        this.formattedTrainNumber = formattedTrainNumber;
    }

}
