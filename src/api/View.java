package api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import inout.Keyboard;

/**
 * IntelliJ IDEA 2021.2.2 (Ultimate Edition)<br>
 * Licensed to Gilberto Rouxinol<br>
 * For educational use only.<br>
 * <p>
 * Polytechnic Institute of Viseu<br>
 * School of Technology and Management of Viseu<br>
 * <p>
 * Class created by Gilberto Rouxinol on 2022<br>
 * Copyright © 2022 Gilberto Rouxinol<br>
 * All rights reserved<br><br>
 * <p>
 * Class that allows the communication with the controller - it's the browser<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class View {

    //AIR - AirViseu
    private final int AIR_OPTION_EXIT = 0;
    private final int AIR_OPTION_CUSTOMER = 1;
    private final int AIR_OPTION_FLIGHT_ATTENDANT = 2;

    //CUST - Customer
    private final int CUST_OPTION_EXIT = 0;
    private final int CUST_OPTION_ENTER_AIRVISEU = 1;
    private final int CUST_OPTION_BUY_AIRPLANE_TICKET = 2;
    private final int CUST_OPTION_CANCEL_AIRPLANE_TICKET = 3;
    private final int CUST_OPTION_CANCEL_AIRPLANE_TICKET_BOOKING = 4;
    private final int CUST_OPTION_ROUTES = 5;
    private final int CUST_OPTION_FLIGHTS_ROUTE = 6;
    private final int CUST_OPTION_PAST_OWN_TRIPS = 7;
    private final int CUST_OPTION_FUTURE_OWN_TRIPS = 8;
    private final int CUST_OPTION_FUTURE_OWN_TRIPS_BOOKING = 9;

    //FA - Flight Attendant
    private final int FA_OPTION_EXIT = 0;
    private final int FA_OPTION_ROUTES = 1;
    private final int FA_OPTION_FLIGHTS_ROUTE = 2;
    private final int FA_OPTION_CUSTOMERS = 3;
    private final int FA_OPTION_CUSTOMERS_FLIGHT = 4;
    private final int FA_OPTION_CUSTOMERS_FLIGHT_BOOKING = 5;
    private final int FA_OPTION_PAST_TRIPS_CUSTOMER = 6;
    private final int FA_OPTION_FUTURE_TRIPS_CUSTOMER = 7;
    private final int FA_OPTION_FUTURE_TRIPS_CUSTOMER_BOOKING = 8;

    private ArrayList<String> menuAirplane;
    private ArrayList<String> menuCustomer;
    private ArrayList<String> menuFlightAttendant;

    private Controller c;

    /**
     *
     * @param c the controller
     */
    public View(Controller c) {
        this.c =c;
        this.menuAirplane = new ArrayList<>();
        this.menuCustomer = new ArrayList<>();
        this.menuFlightAttendant = new ArrayList<>();
        loadMenus();
    }

    /**
     *
     * Load 3 arraylist with the 3 menus:
     *    Air Viseu
     *              Customer
     *              Flight attendant
     */
    private void loadMenus(){
        //Menu: Air Viseu
        menuAirplane.add("DWDM - AirViseu:");
        menuAirplane.add("1 - Customer");
        menuAirplane.add("2 - Flight Attendant");
        menuAirplane.add("0 - Exit");
        menuAirplane.add(null);

        //Menu: Customer
        menuCustomer.add("Customer Menu:");
        menuCustomer.add("1 - Enter or Register AirViseu");
        menuCustomer.add("2 - Buy Airplane Ticket");
        menuCustomer.add("3 - Cancel Airplane Ticket");
        menuCustomer.add("4 - Cancel Airplane Ticket Booking");
        menuCustomer.add("5 - List Routes (Without user ID)");
        menuCustomer.add("6 - List Flights Route (Without user ID)");
        menuCustomer.add("7 - Past Own Trips");
        menuCustomer.add("8 - Future Own Trips");
        menuCustomer.add("9 - Future Own Trips Booking");
        menuCustomer.add("0 - Exit");
        menuCustomer.add(null);

        //Menu: Flight Attendant
        menuFlightAttendant.add("Flight Attendant Menu:");
        menuFlightAttendant.add("1 - List Routes");
        menuFlightAttendant.add("2 - List Flights Route");
        menuFlightAttendant.add("3 - List Customers");
        menuFlightAttendant.add("4 - List Customers Flight");
        menuFlightAttendant.add("5 - List Customers Flight Booking");
        menuFlightAttendant.add("6 - List Past Trips Customer");
        menuFlightAttendant.add("7 - List Future Trips Customer");
        menuFlightAttendant.add("8 - List Future Trips Customer Booking");
        menuFlightAttendant.add("0 - Exit");
        menuFlightAttendant.add(null);
    }

    /**
     * Launch the api Air Viseu
     */
    public void launch(){
        // Reference date to run the program in the testing phase.
        // Obviously, can be replaced by LocalDateTime.now() function instead of LocalDateTime.of(args[]) function.
        LocalDateTime today = LocalDateTime.of(2021,8,22,14,30,15);
        int option;

        while ((option = menu(menuAirplane)) != AIR_OPTION_EXIT) {

            if (option == AIR_OPTION_CUSTOMER) {
                long idUser = 0;
                while ((option = menu(menuCustomer)) != CUST_OPTION_EXIT) {

                    switch (option) {
                        case CUST_OPTION_ENTER_AIRVISEU:
                            System.out.println(" # Enter Air Viseu\n");
                            idUser = c.registerOrEnter();
                            break;
                        case CUST_OPTION_BUY_AIRPLANE_TICKET:
                            System.out.println(" # Buy Airplane Ticket\n");
                            if( idUser != 0 ) c.buyTicket(idUser);
                            break;
                        case CUST_OPTION_CANCEL_AIRPLANE_TICKET:
                            System.out.println(" # Cancel Airplane Ticket\n");
                            if( idUser != 0 ) c.cancelTicket(today, idUser, 1); // jocker 1 is effective ticket
                            break;
                        case CUST_OPTION_CANCEL_AIRPLANE_TICKET_BOOKING:
                            System.out.println(" # Cancel Airplane Ticket Booking\n");
                            if( idUser != 0 ) c.cancelTicket(today, idUser, 2);// jocker 2 is booking ticket
                            break;
                        case CUST_OPTION_ROUTES:
                            System.out.println(" # Show routes");
                            c.showRoutes();
                            break;
                        case CUST_OPTION_FLIGHTS_ROUTE:
                            System.out.println(" # Show flights of one route\n");
                            c.showFlights();
                            break;
                        case CUST_OPTION_PAST_OWN_TRIPS:
                            System.out.println(" # Past Own Trips\n");
                            if( idUser != 0 ) c.showTripsOneCustomer(today, -1, idUser);// If past, pastEffectiveBooking = -1
                            break;                                                      // If flight attendant request idCustomer = idUser
                        case CUST_OPTION_FUTURE_OWN_TRIPS:
                            System.out.println(" # Future Own Trips\n");
                            if( idUser !=0 ) c.showTripsOneCustomer(today, 1, idUser);// If efffective, pastEffectiveBooking = 1
                            break;                                                     // If flight attendant request idCustomer = idUser
                        case CUST_OPTION_FUTURE_OWN_TRIPS_BOOKING:
                            System.out.println(" # Future Own Trips Booking\n");
                            if( idUser !=0 ) c.showTripsOneCustomer(today, 2, idUser);// If booking, pastEffectiveBooking = 2
                            break;                                                     // If flight attendant request idCustomer = idUser
                        case CUST_OPTION_EXIT:
                            System.out.println("Turning off the AirViseu app ... \n");
                            System.exit(0);
                        default:
                            System.out.println("Something is wrong ... exit\n");
                            System.exit(0);
                    }
                }//end while menuCustomer

            }//end if AIR_OPTION_CUSTOMER

            if (option == AIR_OPTION_FLIGHT_ATTENDANT) {

                while ((option = menu(menuFlightAttendant)) != FA_OPTION_EXIT) {
                    switch (option) {
                        case FA_OPTION_ROUTES:
                            System.out.println(" # Show routes");
                            c.showRoutes();
                            break;
                        case FA_OPTION_FLIGHTS_ROUTE:
                            System.out.println(" # Show flights of one route\n");
                            c.showFlights();
                            break;
                        case FA_OPTION_CUSTOMERS:
                            System.out.println(" # Show all customers (ID and full name)\n");
                            c.showCustomers();
                            break;
                        case FA_OPTION_CUSTOMERS_FLIGHT:
                            System.out.println(" # Show the customers of one flight (ID ande full name)\n");
                            c.showCustomersOneFlight(1);
                            break;
                        case FA_OPTION_CUSTOMERS_FLIGHT_BOOKING:
                            System.out.println(" # Show the booking customers of one flight (Id and full name)\n");
                            c.showCustomersOneFlight(2);
                            break;
                        case FA_OPTION_PAST_TRIPS_CUSTOMER:
                            System.out.println(" # Show the past trips of one customer\n");
                            c.showTripsOneCustomer(today, -1, 0);// If past, pastEffectiveBooking = -1
                            break;                                                            // If flight attendant request idCustomer = 0
                        case FA_OPTION_FUTURE_TRIPS_CUSTOMER:
                            System.out.println(" # Show the future trips of one customer\n");
                            c.showTripsOneCustomer(today, 1, 0);// If efffective, pastEffectiveBooking = 1
                            break;                                                           // If flight attendant request idCustomer = 0
                        case FA_OPTION_FUTURE_TRIPS_CUSTOMER_BOOKING:
                            System.out.println(" # Show the future booking trips of one customer\n");
                            c.showTripsOneCustomer(today, 2, 0);// If booking, pastEffectiveBooking = 2
                            break;                                                           // If flight attendant request idCustomer = 0
                        case FA_OPTION_EXIT:
                            System.out.println(" # Turning off the AirViseu app ... \n");
                            System.exit(0);
                        default:
                            System.out.println(" # Something is wrong ... exit\n");
                            System.exit(0);
                    }
                }//end while menuFlightAttendant

            }//end if AIR_OPTION_FLIGHT_ATTENDANT

        }

        System.out.println("Turning off the AirViseu api ... \n");
        System.exit(0);
    }




    /**
     *
     * @param listOptions the list with the options of the menu selected
     * @return the option chosen by the user
     */
    private int menu(ArrayList<String> listOptions){
        while (true) {

            //Display menu options
            for (int i = 0; listOptions.get(i) != null; ++i) {
                System.out.println("\t\t" + listOptions.get(i));
            }

            //Read option
            System.out.println("\t\tEnter one option:\n");
            String enteredValue = Keyboard.readString();

            //Check if the value option is valid
            if (enteredValue.length() < 2) {
                for (int i = 0; listOptions.get(i) != null; ++i) {
                    String str[] = listOptions.get(i).split(" ");
                    if (str[0].equals(enteredValue)) {
                        return Integer.parseInt(enteredValue);
                    }
                }
            }
        }
    }
}
