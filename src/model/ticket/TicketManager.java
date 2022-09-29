package model.ticket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.customer.Customer;
import model.trainline.TrainLineManager;

/**
 * Provides methods for ticket management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class TicketManager extends TrainLineManager {

    private List<Customer> customerList = new LinkedList<Customer>();

    /**
     * Books a ticket for a customer.
     * 
     * @param number
     * @param name
     * @param surname
     */
    protected void book(int number, String name, String surname) {

        /*
         * If there is no such line, it gives an error.
         */
        if (!doesLineExist(number)) {
            System.out.println("Error : A train with the given number does not exist");
            return;

        }

        /*
         * Checks if the customer already exists. If it is the case, it only creates a
         * new ticket; not a new customer.
         */

        Iterator<Customer> iterator = customerList.iterator();

        while (iterator.hasNext()) {

            Customer currentCustomer = iterator.next();
            if (currentCustomer.getName().equals(name) && currentCustomer.getSurname().equals(surname)) {
                Ticket ticket = new Ticket(number, currentCustomer);
                addTicketToTrainLine(ticket);
                System.out.println(ticket.getBillNumber() + ";" + ticket.getCustomer().getCustomerNumber());

                return;
            }

        }
        /*
         * Creates a new customer and a new ticket.
         */
        Customer customer = new Customer(name, surname);
        Ticket ticket = new Ticket(number, customer);
        customerList.add(customer);
        addTicketToTrainLine(ticket);
        System.out.println(ticket.getBillNumber() + ";" + ticket.getCustomer().getCustomerNumber());

    }

}
