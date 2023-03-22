package api;

import airplane.Customer;
import airplane.Flight;
import airplane.Route;
import airplane.Ticket;
import inout.Keyboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
 * Class that handle all request make in the view and return the response in the view<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Controller {

    private final int MAX_BOOKING = 4;

    private Model m;
    private String folder;
    private DateTimeFormatter formatterLDT;
    private DateTimeFormatter formatterLT;




    /**
     *
     * Controler constructor
     * @param model the all data of the API
     * @param folder the full path of the folder where are the indispensable and dispensable files
     */
    public Controller(Model model, String folder) {
        this.m = model;
        this.folder = folder;
        this.formatterLDT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.formatterLT = DateTimeFormatter.ofPattern("HH:mm:ss");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // REGISTER OR ENTER API AIR VISEU
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Checking API Access:
     *   If the user exists, its existence is only verified.
     *   If the user does not exist, a new registration is made.
     * @return id, by setRegister method, the new customer ID
     *      or idEnter, by getRegister method, the existent customer ID
     */
    public long registerOrEnter(){
        int flag = 1;
        String strDate;
        do {
            System.out.println("Do you have an ID (1 - Yes | 0 - No):");
            strDate = Keyboard.readString();
            if (Pattern.matches("^0$|^1$", strDate)) flag = 0;
        } while(flag == 1);

        if (strDate.equals("1")){
            return getRegister();
        }else{
            return setRegister();
        }
    }




    // -----------------------------------------------------------------------------------------------------------------
    // GET REGISTER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Get a existent register
     * @return idEnter, the existent customer ID
     */
    private long getRegister(){
        System.out.println("Enter the customer ID:");
        long idEnter = Keyboard.readInteger();

        ArrayList<Customer> list = m.getCustomersList();
        for (int i = 0; i < list.size(); i++) {
            if (idEnter == list.get(i).getId()) {
                System.out.println("Welcome to the AirViseu app - " + list.get(i).getFullName());
                return idEnter;
            }
        }

        System.out.println("Unknown ID. You'll be redirected.");
        return setRegister();
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SET REGISTER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Set a new register
     * @return id, the new customer ID
     */
    private long setRegister(){
        long id = 0;
        int flag = 1;
        String strDate;
        do {
            System.out.println("Enter a valid customer ID (9 digits and the first non-zero):");
            strDate = Keyboard.readString();
            if (Pattern.matches("^[1-9][0-9]{8}", strDate)) {
                id = Long.parseLong(strDate);
                flag = 0;
            }
        } while (flag == 1);

        System.out.println("Enter the full name:");
        String name = Keyboard.readString();

        System.out.println("Enter the job name:");
        String job = Keyboard.readString();

        System.out.println("Enter the address:");
        String address = Keyboard.readString();

        strDate = enterDate();

        LocalDate birthday = LocalDate.parse(strDate);

        Customer c = new Customer(id, name, job, address, birthday);

        ArrayList<Customer> list = m.getCustomersList();
        list.add(c);
        m.setCustomersList(list);

        String fileNameCustomer = folder + "customers.txt";
        ArrayList<String> listStr = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getId() + ", " +
                    list.get(i).getFullName() + ", " +
                    list.get(i).getJob() + ", " +
                    list.get(i).getAddress() + ", " +
                    list.get(i).getBirthday().getYear()+ ", " +
                    list.get(i).getBirthday().getMonthValue() + ", " +
                    list.get(i).getBirthday().getDayOfMonth();
            listStr.add(str);
        }
        m.writerFileText(fileNameCustomer, listStr);

        return id;
    }




    // -----------------------------------------------------------------------------------------------------------------
    // BUY TICKET
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Buy a ticket
     * @param id  the ticket purchaser customer ID
     */
    public void buyTicket(long id) {
        System.out.println("Enter the route:");
        int r = Keyboard.readInteger();

        System.out.println("Enter the flight:");
        int f = Keyboard.readInteger();

        System.out.print("Trip: ");
        String strDateTrip = enterDate();
        System.out.print("Trip: ");
        String strTimeTrip = enterTime();
        String strDateTimeTrip = strDateTrip + "T" + strTimeTrip + ".000";
        LocalDateTime dtTrip = LocalDateTime.parse(strDateTimeTrip);

        System.out.print("Purchase: ");
        String strDatePurchase = enterDate();
        System.out.print("Purchase: ");
        String straTimePurchase = enterTime();
        String strDateTimePurchase = strDatePurchase + "T" + straTimePurchase + ".000";
        LocalDateTime dtPurchase = LocalDateTime.parse(strDateTimePurchase);

        ArrayList<Route> listR = new ArrayList<>();
        listR = m.getRoutesList();
        double km = 0;

        for (int i = 0; i < listR.size(); i++) {
            if (listR.get(i).getIdRoute() == r) {
                km = listR.get(i).getDistance();
                break;
            }
        }
        double price = m.getPriceRules().makePriceTrip(km);

        ArrayList<Flight> listF = new ArrayList<>();
        listF = m.getFlightsList();
        int maxSeat = 0;
        for (int i = 0; i < listF.size(); i++) {
            if (listF.get(i).getIdRoute() == r && listF.get(i).getIdFlight() == f) {
                maxSeat = listF.get(i).getAirp().getNumberSeats();
                break;
            }
        }

        ArrayList<Ticket> listT = new ArrayList<>();
        listT = m.getTicketsList();
        int countJockey1 = 0;
        int countJockey2 = 0;
        for (int i = 0; i < listT.size(); i++) {
            if (listT.get(i).getIdRoute() == r && listT.get(i).getIdFlight() == f && listT.get(i).getDateTrip().equals(dtTrip) && listT.get(i).getJockey() == 1) {
                ++countJockey1;
            }
            if (listT.get(i).getIdRoute() == r && listT.get(i).getIdFlight() == f && listT.get(i).getDateTrip().equals(dtTrip) && listT.get(i).getJockey() == 2) {
                ++countJockey2;
            }
        }
        System.out.println("Max seats: " + maxSeat + " | Seats already sold: " + countJockey1 + " | Seats already booking: " + countJockey2 + " | Max Seat booking: " + MAX_BOOKING);
        int jockey = 0;
        if (countJockey1 < maxSeat) {
            System.out.println("Effective ticket.");
            jockey = 1;
        } else if (countJockey2 < MAX_BOOKING) {
            System.out.println("Booking ticket.");
            jockey = 2;
        } else {
            System.out.println("Flight unavailable.");
            jockey = -1;
        }

        // Upload all tickets
        if (jockey != -1) {
            Ticket t = new Ticket(id, r, f, dtTrip, dtPurchase, price, jockey);

            listT.add(t);
            m.setTicketsList(listT);

            String fileNameTicket = folder + "tickets.txt";
            ArrayList<String> listStr = new ArrayList<>();
            for (int i = 0; i < listT.size(); i++) {
                String str = listT.get(i).getCustomerID() + ", " +
                        listT.get(i).getIdRoute() + ", " +
                        listT.get(i).getIdFlight() + ", " +
                        listT.get(i).getDateTrip().getYear() + ", " +
                        listT.get(i).getDateTrip().getMonthValue() + ", " +
                        listT.get(i).getDateTrip().getDayOfMonth() + ", " +
                        listT.get(i).getDateTrip().getHour() + ", " +
                        listT.get(i).getDateTrip().getMinute() + ", " +
                        listT.get(i).getDateTrip().getSecond() + ", " +
                        listT.get(i).getDataPurchase().getYear() + ", " +
                        listT.get(i).getDataPurchase().getMonthValue() + ", " +
                        listT.get(i).getDataPurchase().getDayOfMonth() + ", " +
                        listT.get(i).getDataPurchase().getHour() + ", " +
                        listT.get(i).getDataPurchase().getMinute() + ", " +
                        listT.get(i).getDataPurchase().getSecond() + ", " +
                        listT.get(i).getPrice() + ", " +
                        listT.get(i).getJockey();
                listStr.add(str);
            }
            m.writerFileText(fileNameTicket, listStr);
            System.out.println("Ticket submit successfully.");
        }
    }




    // -----------------------------------------------------------------------------------------------------------------
    // CANCEL EFFECTIVE TICKET (jk = 1) OR CANCEL BOOKING TICKET (jk = 2)
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Cancel an effective ticket or cancel a booking ticket
     * @param id the customr ID
     * @param jk the jockey that sets the intended ticket state - effective or booking
     */
    public void cancelTicket(LocalDateTime today, long id, int jk){ // jk = 1 if effective or jk = 2 if booking
        showTripsOneCustomer(today,  jk,  id);

        System.out.println("Enter the route:");
        int r = Keyboard.readInteger();

        System.out.println("Enter the flight:");
        int f = Keyboard.readInteger();

        String strDateTrip = enterDate();
        String strTimeTrip = enterTime();
        String strDateTimeTrip = strDateTrip + "T" + strTimeTrip + ".000";
        LocalDateTime dtTrip = LocalDateTime.parse(strDateTimeTrip);

        ArrayList<Ticket> listT = new ArrayList<>();
        listT = m.getTicketsList();
        int countJockey2 = 0;
        int indexTicket = 0;
        for (int i = listT.size()-1; i >= 0 ; i--) {
            if(listT.get(i).getIdRoute() == r && listT.get(i).getIdFlight() == f && listT.get(i).getDateTrip().equals(dtTrip) && listT.get(i).getJockey() == 2) {
                ++countJockey2;
                indexTicket = i; //The first booking ticket of this flight. That's why the list is read backwards.
            }
        }

        // For effective tickets - It is necessary to update the booking tickets afterwards
        if (jk == 1) {
            for (int i = 0; i < listT.size(); i++) {
                if (listT.get(i).getCustomerID() == id && listT.get(i).getIdRoute() == r && listT.get(i).getIdFlight() == f && listT.get(i).getDateTrip().equals(dtTrip) && listT.get(i).getJockey() == 1) {
                    listT.get(i).setJockey(0);
                    if(countJockey2 != 0){
                        listT.get(indexTicket).setJockey(1); // Promoting the first booking ticket to an effective ticket.
                    }
                    break;
                }
            }
        }

        // For booking tickets - Just change the state
        if (jk == 2) {
            for (int i = 0; i < listT.size(); i++) {
                if (listT.get(i).getCustomerID() == id && listT.get(i).getIdRoute() == r && listT.get(i).getIdFlight() == f && listT.get(i).getDateTrip().equals(dtTrip) && listT.get(i).getJockey() == 2) {
                    listT.get(i).setJockey(0);
                    break;
                }
            }
        }

        // Update and upload all tickets
        String fileNameTicket = folder + "tickets.txt";
        ArrayList<String> listStr = new ArrayList<>();
        for (int i = 0; i < listT.size(); i++) {
            String str = listT.get(i).getCustomerID() + ", " +
                    listT.get(i).getIdRoute() + ", " +
                    listT.get(i).getIdFlight() + ", " +
                    listT.get(i).getDateTrip().getYear() + ", " +
                    listT.get(i).getDateTrip().getMonthValue() + ", " +
                    listT.get(i).getDateTrip().getDayOfMonth() + ", " +
                    listT.get(i).getDateTrip().getHour() + ", " +
                    listT.get(i).getDateTrip().getMinute() + ", " +
                    listT.get(i).getDateTrip().getSecond() + ", " +
                    listT.get(i).getDataPurchase().getYear() + ", " +
                    listT.get(i).getDataPurchase().getMonthValue() + ", " +
                    listT.get(i).getDataPurchase().getDayOfMonth() + ", " +
                    listT.get(i).getDataPurchase().getHour() + ", " +
                    listT.get(i).getDataPurchase().getMinute() + ", " +
                    listT.get(i).getDataPurchase().getSecond() + ", " +
                    listT.get(i).getPrice() + ", " +
                    listT.get(i).getJockey();
            listStr.add(str);
        }
        m.writerFileText(fileNameTicket, listStr);
        System.out.println("Ticket canceled successfully.");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SHOW ROUTES
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Show all routes
     */
    public void showRoutes(){
        ArrayList<Route> list = m.getRoutesList();
        System.out.println("\nRoutes:");
        int dashed = 48;
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.format("\n%-6s  %-8s  %-18s  %-18s\n", "ROUTE", "FLIGHT", "DESTINATION", "DISTANCE");
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" ");
        for (int i = 0; i < list.size(); i++) {
            Route r = list.get(i);
            System.out.format("%6d  %8d  %-18s  %-18.2f\n",
                    r.getIdRoute(), r.getMaxFlight(),r.getDestination(),r.getDistance());
        }
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" \n");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SHOW FLIGHTS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Show all flights
     */
    public void showFlights(){
        ArrayList<Flight> list = m.getFlightsList();

        System.out.println("Enter the route:");
        int r = Keyboard.readInteger();

        System.out.println("\nFlights of one route:");
        int dashed = 65;
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.format("\n%-6s  %-12s  %-8s  %-24s  %-6s", "FLIGHT", "DAY", "TIME", "AIRPLANE", "SEATS\n");
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" ");
        for (int i = 0; i < list.size(); i++) {
            Flight f = list.get(i);
            if (f.getIdRoute() == r) {
                System.out.format("%6d  %-12s  %8s  %-24s  %6d \n",
                        f.getIdFlight(), f.getDay(), f.getTime().format(formatterLT), f.getAirp().getNameAirplane(), f.getAirp().getNumberSeats());
            }
        }
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" \n");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SHOW CUSTOMERS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Show all customers
     */
    public void showCustomers(){
        ArrayList<Customer> list = m.getCustomersList();
        System.out.println("\nCustomers:");
        int dashed = 64;
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.format("\n%-12s  %-50s\n", "ID", "NAME");
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" ");
        for (int i = 0; i < list.size(); i++) {
            Customer c = list.get(i);
            if( i%10 == 0 && i > 0){
                for (int j = 0; j < dashed-4; j++) System.out.print("-");
                System.out.format("%4d\n",i);
            }
            System.out.format("%12d  %-50s\n", c.getId(), c.getFullName());
        }
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" \n");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SHOW CUSTOMERS OF ONE FLIGHT
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Show all customers that have effective ticket of a flight or show all customers that have booking ticket of a flight
     * @param effectiveOrBooking the status of the ticket (Effective ticket - 1 | Booking ticket - 2)
     */
    public void showCustomersOneFlight(int effectiveOrBooking){
        ArrayList<Ticket> list = m.getTicketsList();

        System.out.println("Enter the route:");
        int r = Keyboard.readInteger();

        System.out.println("Enter the flight:");
        int f = Keyboard.readInteger();

        String strDate = enterDate();

        String strTime = enterTime();

        String strDateTime = strDate + "T" + strTime + ".000";
        LocalDateTime dt = LocalDateTime.parse(strDateTime);

        System.out.println("\nCustomers of one flight:");
        System.out.println("\nRoute: " + r + " Flight: " + f + " Schedule: " + strDateTime + " SEATS: " + searchSeatsKnowingFlight(f));
        int dashed = 64;
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.format("\n%-12s  %-50s\n", "ID", "NAME");
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" ");
        int occupiedSeats = 0;
        for (int i = 0; i < list.size(); i++) {
            Ticket t = list.get(i);
            if (t.getIdRoute() == r && t.getIdFlight() == f && t.getDateTrip().equals(dt) && t.getJockey() == effectiveOrBooking){
                System.out.format("%12d  %-50s\n", t.getCustomerID(), searchCustomerNameKnowingID(t.getCustomerID()));
                ++occupiedSeats;
            }
        }
        for (int i = 0; i < dashed-4; i++) System.out.print("-");
        System.out.format("%4d\n",occupiedSeats);
        System.out.println(" \n");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SHOW TRIPS OF ONE CUSTOMER
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Show trips of one customer
     * @param today the day considered as the day today
     * @param pastEffectiveBooking the status ticket (Past - -1 | Effestive - 1 | Booking - 2)
     * @param idCustomer the status customer:
     *        idCustomer - 0, the method showTripsOneCustomer() run in mode flight attendant (the order comes from an administrator)
     *        idCustomer - real ID, the method showTripsOneCustomer() run in mode customer (the order comes from a customer)
     */
    public void showTripsOneCustomer(LocalDateTime today, int pastEffectiveBooking, long idCustomer){
        ArrayList<Ticket> list = m.getTicketsList();

        long id;
        if(idCustomer == 0) {
            System.out.println("Enter the customer ID:");
            id = Keyboard.readInteger();
        }else{
            id = idCustomer;
        }

        if(pastEffectiveBooking == -1)
            System.out.println("\nPast trip (Today is "+ today.format(formatterLDT) +"):");
        else if(pastEffectiveBooking == 1)
            System.out.println("\nEffective trip (Today is "+ today.format(formatterLDT) +"):");
        else
            System.out.println("\nBooking trip (Today is "+ today.format(formatterLDT) +"):");

        System.out.println("Customer ID: " + id);
        System.out.println("Customer name: " + searchCustomerNameKnowingID(id));
        int dashed = 82;
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.format("\n%-8s  %-8s  %-16s  %-22s  %-22s\n", "ROUTE", "FLIGHT", "DESTINATION", "SCHEDULE", "PURCHASE");
        for (int i = 0; i < dashed; i++) System.out.print("-");
        System.out.println(" ");
        int numberTrips = 0;

        if (pastEffectiveBooking == -1) {
            for (int i = 0; i < list.size(); i++) {
                Ticket t = list.get(i);
                if (t.getCustomerID() == id && today.isAfter(t.getDateTrip()) && t.getJockey() == 1) {
                    System.out.format("%8d  %8d  %-16s  %-22s  %-22s\n",
                            t.getIdRoute(), t.getIdFlight(), searchDestinationNameKnowingRoute(t.getIdRoute()),
                            t.getDateTrip().format(formatterLDT), t.getDataPurchase().format(formatterLDT));
                    ++numberTrips;
                }
            }
        }

        if (pastEffectiveBooking == 1 || pastEffectiveBooking == 2) {
            for (int i = 0; i < list.size(); i++) {
                Ticket t = list.get(i);
                if (t.getCustomerID() == id && today.isBefore(t.getDateTrip()) && t.getJockey() == pastEffectiveBooking) {
                    System.out.format("%8d  %8d  %-16s  %-22s  %-22s\n",
                            t.getIdRoute(), t.getIdFlight(), searchDestinationNameKnowingRoute(t.getIdRoute()),
                            t.getDateTrip().format(formatterLDT), t.getDataPurchase().format(formatterLDT));
                    ++numberTrips;
                }
            }
        }

        for (int i = 0; i < dashed-4; i++) System.out.print("-");
        System.out.format("%4d\n",numberTrips);
        System.out.println(" \n");
    }




    // -----------------------------------------------------------------------------------------------------------------
    // ENTER THE DATE
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Read data date following the rule: yyyy-mm-dd
     * @return strDate, a valid string date
     */
    private String enterDate(){
        int flag = 1;
        String strDate = "";
        do {
            System.out.println("Enter the date (yyyy-mm-dd):");
            strDate = Keyboard.readString();    // ALT+Enter + Check RegExp
            if (Pattern.matches("(20[0-9][0-9]|19[0-9][0-9])-(0[1-9]|1[012])-(3[01]|[12][0-9]|0[1-9])", strDate)) {
                flag = 0;
            }
        } while(flag == 1);
        return strDate;
    }




    // -----------------------------------------------------------------------------------------------------------------
    // ENTER THE DATE
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Read data time following the rule: hh:mm:ss
     * @return strTime, a valid string time
     */
    private String enterTime(){
        String strTime = "";
        int flag = 1;
        do {
            System.out.println("Enter the time (hh:mm:ss):");
            strTime = Keyboard.readString();
            if (Pattern.matches("([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", strTime)) {
                flag = 0;
            }
        } while(flag == 1);
        return strTime;
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SEARCH SEATS KNOWING THE FLIGHT
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Search the number seats of a flight knowing the flight ID
     * @param idFlight the flight ID
     * @return nSeats, the number of flight seats
     */
    private int searchSeatsKnowingFlight(int idFlight){
        ArrayList<Flight> list = m.getFlightsList();
        int nSeats = 0;
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getIdFlight() == idFlight){
                nSeats = list.get(i).getAirp().getNumberSeats();
                break;
            }
        }
        return nSeats;
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SEARCH DESTINATION NAME KNOWING THE ROUTE
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Search the destination name knowing the route ID
     * @param idRoute the route ID
     * @return str, the destination name
     */
    private String searchDestinationNameKnowingRoute(int idRoute){
        ArrayList<Route> list = m.getRoutesList();
        String str = "";
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getIdRoute() == idRoute){
                str = list.get(i).getDestination();
                break;
            }
        }
        return str;
    }




    // -----------------------------------------------------------------------------------------------------------------
    // SEARCH CUSTOMER NAME KNOWING THE ID
    // -----------------------------------------------------------------------------------------------------------------

    /**
     *
     * Search customer name knowing customer ID
     * @param id the customer ID
     * @return str, the customer name
     */
    private String searchCustomerNameKnowingID(long id){
        ArrayList<Customer> list = m.getCustomersList();
        String str = "";
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getId() == id){
                str = list.get(i).getFullName();
                break;
            }
        }
        return str;
    }
}
