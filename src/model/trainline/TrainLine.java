package model.trainline;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.currency.Currency;
import model.ticket.Ticket;
import model.train.Train;
import model.trainstation.TrainStation;

/**
 * Represents a train line.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class TrainLine implements Comparable<TrainLine> {
    private Train train = new Train();
    private TrainStation start;
    private TrainStation destination;
    private double price;
    private Currency currency;
    private List<Ticket> tickets = new LinkedList<Ticket>();

    /**
     * Creates a new train line.
     * 
     * @param trainNumber
     * @param start
     * @param destination
     * @param price
     * @param currency
     */
    public TrainLine(int trainNumber, TrainStation start, TrainStation destination, double price, String currency) {
        this.train.setTrainNumber(trainNumber);
        this.start = start;
        this.destination = destination;
        this.price = price;
        Currency curr = Currency.valueOf(Currency.class, currency);
        this.currency = curr;

    }

    /**
     * Prints all tickets of the trainline.
     */
    public void printTickets() {
        Iterator<Ticket> iterator = tickets.iterator();
        if (tickets.isEmpty()) {
            return;
        }
        while (iterator.hasNext()) {
            Ticket currentTicket = iterator.next();
            System.out.println(currentTicket.toString());
        }
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TrainStation getDestination() {
        return destination;
    }

    public void setDestination(TrainStation destination) {
        this.destination = destination;
    }

    public TrainStation getStart() {
        return start;
    }

    public void setStart(TrainStation start) {
        this.start = start;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int compareTo(TrainLine t) {
        if (this.train.getTrainNumber() < t.getTrain().getTrainNumber()) {
            return -1;
        } else if (this.train.getTrainNumber() > t.getTrain().getTrainNumber()) {
            return 1;
        }

        return 0;
    }

}
