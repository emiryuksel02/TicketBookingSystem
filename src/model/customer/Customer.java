package model.customer;

/**
 * Represents a customer
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class Customer {
    private final int customerNumber;
    private static int nextNum = 1;
    private final String name;
    private final String surname;

    /**
     * Creates a new customer.
     * 
     * @param name
     * @param surname
     */
    public Customer(String name, String surname) {
        this.customerNumber = nextNum++;
        this.name = name;
        this.surname = surname;

    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}