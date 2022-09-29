package model.trainline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import model.ticket.Ticket;
import model.ticket.TicketCustomerNumberComparator;
import model.trainstation.TrainStation;

/**
 * Provides methods for managing a trainline.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class TrainLineManager {
    private static final String LINE_NOT_EXIST = "Error , a train line with the given number does not exist.";
    private static final String LINE_DOES_EXIST = "Error , a train line with the given number already exists.";

    private List<TrainLine> lines = new LinkedList<TrainLine>();

    /**
     * Adds a train line to the system.
     * 
     * @param number
     * @param start
     * @param destination
     * @param price
     * @param currency
     */
    protected void add(int number, String start, String destination, Double price, String currency) {
        if (doesLineExist(number)) {
            System.out.println(LINE_DOES_EXIST);
            return;
        }
        TrainStation startStation = new TrainStation(start);
        TrainStation destinationStation = new TrainStation(destination);
        TrainLine addedLine = new TrainLine(number, startStation, destinationStation, price, currency);

        lines.add(addedLine);

        System.out.println("OK");
    }

    /**
     * Adds a ticket to the correct train line.
     * 
     * @param ticket
     */
    protected void addTicketToTrainLine(Ticket ticket) {
        Iterator<TrainLine> iterator = lines.iterator();
        while (iterator.hasNext()) {
            TrainLine currentLine = iterator.next();
            if (currentLine.getTrain().getTrainNumber() == ticket.getTrainNumber()) {
                List<Ticket> tickets = currentLine.getTickets();
                tickets.add(ticket);
                currentLine.setTickets(tickets);
            }
        }
    }

    /**
     * Removes the line with the given train number.
     * 
     * @param number
     */
    protected void remove(int number) {
        if (!doesLineExist(number)) {
            System.out.println(LINE_NOT_EXIST);
            return;
        }
        Iterator<TrainLine> iterator = lines.iterator();

        while (iterator.hasNext()) {
            TrainLine currentLine = iterator.next();
            if (currentLine.getTrain().getTrainNumber() == number) {
                iterator.remove();

            }
        }
        System.out.println("OK");
    }

    /**
     * Prints bookable train lines in the ascending order based on their train
     * numbers.
     */
    protected void listRoute() {
        if (this.lines.isEmpty()) {
            return;
        }
        PriorityQueue<TrainLine> lineQ = new PriorityQueue<>();

        Iterator<TrainLine> iterator = lines.iterator();
        while (iterator.hasNext()) {
            TrainLine currentLine = iterator.next();
            lineQ.add(currentLine);
        }

        Iterator<TrainLine> iteratorQ = lineQ.iterator();
        while (iteratorQ.hasNext()) {
            System.out.println(toString(lineQ.poll()));
        }

    }

    /**
     * Returns a train line with this format: < train number >;< start >;<
     * destination >;< price >;<currency>
     * 
     * @param trainline
     * @return
     */
    protected String toString(TrainLine trainline) {
        int trainNumber = trainline.getTrain().getTrainNumber();
        String formattedTrainNumber = String.format("%03d", trainNumber);

        String start = trainline.getStart().getId();
        String destination = trainline.getDestination().getId();
        String price = Double.toString(trainline.getPrice());
        String currency = trainline.getCurrency().name();

        return formattedTrainNumber + ";" + start + ";" + destination + ";" + price + ";" + currency;

    }

    /**
     * Lists bookings in ascending order according to the customer number. If the
     * customer number is identical, sorts in descending order according to the
     * billing number. The algorithm is similar to radix sort. It sorts according to
     * second important paramater, which is here billing number. Than it sorts based
     * on most important parameter, customer number. In priority queue if the values
     * are same, it means compareTo() method returns 0 and queue takes the first
     * element it finds, which is really similar to Bucket Sort.
     * 
     */

    protected void listBookings() {
        PriorityQueue<Ticket> ticketQ = new PriorityQueue<>();
        PriorityQueue<TicketCustomerNumberComparator> trainNumQ = new PriorityQueue<>();
        List<Ticket> billNumberQ = new ArrayList<Ticket>();
        List<TicketCustomerNumberComparator> numberComperator = new ArrayList<TicketCustomerNumberComparator>();

        Iterator<TrainLine> iterator = lines.iterator();

        while (iterator.hasNext()) {
            ticketQ.addAll(iterator.next().getTickets());

        }
        if (ticketQ.isEmpty()) {
            return;
        }
        Iterator<Ticket> iteratorQ = ticketQ.iterator();
        while (iteratorQ.hasNext()) {
            billNumberQ.add(ticketQ.poll());
        }

        for (Ticket t : billNumberQ) {
            numberComperator.add(new TicketCustomerNumberComparator(t.getCustomer().getCustomerNumber(),
                    t.getTrainNumber(), t.getBillNumber()));
        }

        trainNumQ.addAll(numberComperator);

        Iterator<TicketCustomerNumberComparator> lastQueue = trainNumQ.iterator();
        while (lastQueue.hasNext()) {
            System.out.println(trainNumQ.poll().toString());
        }

    }

    /**
     * Checks if a train line with the given number exists.
     * 
     * @param num
     * @return
     */
    protected boolean doesLineExist(int num) {
        Iterator<TrainLine> iterator = getLines().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getTrain().getTrainNumber() == num) {
                return true;
            }
        }

        return false;
    }

    protected List<TrainLine> getLines() {
        return this.lines;
    }

}
