package ui;

import java.util.Scanner;

import model.TicketBookingSystem;

/**
 * User interface of the application.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class TicketBookingSystemUI {
    private static final String COMMAND_SEPARATOR = " ";
    private static final String PARAMETER_SEPARATOR = ";";

    private static final String COMMAND_ADD = "add";
    private static final int ADD_PARAMETER_TRAINNUMBER = 0;
    private static final int ADD_PARAMETER_START = 1;
    private static final int ADD_PARAMETER_DESTINATION = 2;
    private static final int ADD_PARAMETER_PRICE = 3;
    private static final int ADD_PARAMETER_CURRENCY = 4;

    private static final String COMMAND_REMOVE = "remove";
    private static final int REMOVE_PARAMETER_TRAINNUMBER = 0;

    private static final String COMMAND_LISTROUTE = "list-route";

    private static final String COMMAND_BOOK = "book";
    private static final int BOOK_PARAMETER_TRAINNUMBER = 0;
    private static final int BOOK_PARAMETER_NAME = 1;
    private static final int BOOK_PARAMETER_SURNAME = 2;

    private static final String COMMMAND_LISTBOOKINGS = "list-bookings";

    private static final String COMMAND_QUIT = "quit";

    private static final String PLEASE_ENTER_VALID_COMMAND = "Error , please enter a valid command.";
    private static final String INPUT_WRONG_FORMAT = "Error , input is in wrong format. "
            + "Please make sure you have entered all parameters correctly and separated them with ';'.";

    private static final String SHOULD_BE_NUMBER = "Error , these parameters should be numbers"
            + " : train number(must be an integer), price.";
    private static final String CURRENCY_NOT_ACCEPTABLE = "Error , please select one of these currencies. "
            + ": EUR, JPY, USD";
    private static final String ERROR_TRAINNUMBER = "Error , train number should be an integer.";

    private TicketBookingSystem bookingSystem = new TicketBookingSystem();

    /**
     * Constructs a new ticket booking system UI.
     * 
     * @param bookingSystem
     */
    public TicketBookingSystemUI(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    /**
     * Starts an interactive session.
     */
    public void interactive() {
        boolean quit = false;

        Scanner scanner = new Scanner(System.in);

        do {
            String input = scanner.nextLine();
            String[] split = input.split(COMMAND_SEPARATOR);
            String command = split[0];
            String[] parameters = (split.length > 1) ? split[1].split(PARAMETER_SEPARATOR) : null;

            switch (command) {
            case COMMAND_ADD:
                add(parameters);
                break;

            case COMMAND_REMOVE:
                remove(parameters);
                break;

            case COMMAND_LISTROUTE:
                listRoute();
                break;

            case COMMAND_BOOK:
                book(parameters);
                break;

            case COMMMAND_LISTBOOKINGS:
                listBookings();
                break;

            case COMMAND_QUIT:
                quit = true;
                break;

            default:
                System.out.println(PLEASE_ENTER_VALID_COMMAND);
                break;

            }

        } while (!quit);
        scanner.close();

    }

    private void add(String[] parameters) {
        if (parameters == null || parameters.length != 5) {
            System.out.println(INPUT_WRONG_FORMAT);
            return;
        }
        try {
            int trainNumber = Integer.valueOf(parameters[ADD_PARAMETER_TRAINNUMBER]);
            String start = parameters[ADD_PARAMETER_START];
            String destination = parameters[ADD_PARAMETER_DESTINATION];
            double price = Double.valueOf(parameters[ADD_PARAMETER_PRICE]);
            String currency = parameters[ADD_PARAMETER_CURRENCY];

            this.bookingSystem.addLine(trainNumber, start, destination, price, currency);
        } catch (NumberFormatException n) {
            System.out.println(SHOULD_BE_NUMBER);
        } catch (IllegalArgumentException i) {
            System.out.println(CURRENCY_NOT_ACCEPTABLE);
        }

    }

    private void remove(String[] parameters) {
        if (parameters == null) {
            System.out.println(INPUT_WRONG_FORMAT);
            return;
        }

        try {
            int trainNumber = Integer.valueOf(parameters[REMOVE_PARAMETER_TRAINNUMBER]);

            this.bookingSystem.removeLine(trainNumber);
        } catch (NumberFormatException n) {
            System.out.println(ERROR_TRAINNUMBER);
        }

    }

    private void listRoute() {

        this.bookingSystem.listLines();

    }

    private void book(String[] parameters) {
        if (parameters == null || parameters.length != 3) {
            System.out.println(INPUT_WRONG_FORMAT);
            return;
        }

        try {
            int trainNumber = Integer.valueOf(parameters[BOOK_PARAMETER_TRAINNUMBER]);
            String name = parameters[BOOK_PARAMETER_NAME];
            String surname = parameters[BOOK_PARAMETER_SURNAME];

            this.bookingSystem.bookTicket(trainNumber, name, surname);
        } catch (NumberFormatException n) {
            System.out.println(ERROR_TRAINNUMBER);
        }

    }

    private void listBookings() {

        this.bookingSystem.printBookings();

    }

}