import model.TicketBookingSystem;
import ui.TicketBookingSystemUI;

/**
 * This program provides a ticket booking system for train tickets.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public final class Main {
    private Main() {

    }

    /**
     * Main entry point to the app.
     * 
     * @param args
     */
    public static void main(String[] args) {

        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        TicketBookingSystemUI bookingSystemUI = new TicketBookingSystemUI(bookingSystem);
        bookingSystemUI.interactive();

    }

}
