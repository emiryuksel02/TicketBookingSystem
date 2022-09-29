package model;

import model.ticket.TicketManager;

/**
 * Brings methods for ticket and train line together and makes them more
 * readable.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class TicketBookingSystem extends TicketManager {

    public void addLine(int number, String start, String destination, double price, String currency) {
        this.add(number, start, destination, price, currency);
    }

    public void removeLine(int number) {
        this.remove(number);
    }

    public void listLines() {
        this.listRoute();
    }

    public void bookTicket(int number, String name, String surname) {
        this.book(number, name, surname);
    }

    public void printBookings() {
        this.listBookings();
    }

}