package api;

import airplane.*;
import inout.FileText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
 * Class with all data<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Model {

    private String fullPathAirplanes;
    private String fullPathCustomers;
    private String fullPathFlights;
    private String fullPathRoutes;
    private String fullPathTickets;
    private String fullPathPrices;

    private ArrayList<Airplane> airplanesList;
    private ArrayList<Customer> customersList;
    private ArrayList<Flight> flightsList;
    private ArrayList<Route> routesList;
    private ArrayList<Ticket> ticketsList;

    private Prices priceRules;




    /**
     *
     * Model constructor - 6 parameters
     * The full path is the folder name concatenated with the load file name
     * @param fullPathAirplanes the full path of airplanes data
     * @param fullPathFlights the full path of flights data
     * @param fullPathRoutes the full path of routes data
     * @param fullPathPrices the full path of prices data
     * @param fullPathCustomers the full path of customers data
     * @param fullPathTickets the full path of tickets data
     */
    public Model(String fullPathAirplanes,  String fullPathFlights, String fullPathRoutes, String fullPathPrices, String fullPathCustomers, String fullPathTickets) {

        this.fullPathAirplanes = fullPathAirplanes;
        this.fullPathFlights = fullPathFlights;
        this.fullPathRoutes = fullPathRoutes;
        this.fullPathPrices = fullPathPrices;
        this.fullPathCustomers = fullPathCustomers;
        this.fullPathTickets = fullPathTickets;

        this.airplanesList = new ArrayList<>();
        this.flightsList = new ArrayList<>();
        this.routesList = new ArrayList<>();
        this.customersList = new ArrayList<>();
        this.ticketsList = new ArrayList<>();

        dataAirplanes();
        dataFlights();// after airplanes objects
        dataRoutes();
        dataPrices();
        dataCustomers();
        dataTickets();
    }




    /**
     *
     * Model constructor - 4 parameters
     * The full path is the folder name concatenated with the load file name
     * @param fullPathAirplanes the full path of airplanes data
     * @param fullPathFlights the full path of flights data
     * @param fullPathRoutes the full path of routes data
     * @param fullPathPrices the full path of prices data
     */
    public Model(String fullPathAirplanes,  String fullPathFlights, String fullPathRoutes, String fullPathPrices) {

        this.fullPathAirplanes = fullPathAirplanes;
        this.fullPathFlights = fullPathFlights;
        this.fullPathRoutes = fullPathRoutes;
        this.fullPathPrices = fullPathPrices;

        this.airplanesList = new ArrayList<>();
        this.flightsList = new ArrayList<>();
        this.routesList = new ArrayList<>();

        this.ticketsList = new ArrayList<>();
        this.customersList = new ArrayList<>();

        dataAirplanes();
        dataFlights();
        dataRoutes();
        dataPrices();
    }




    /**
     *
     * The method open, read and close one text file
     * @param fullPath the full path: the folder name concatenated with the load file name
     * @return list, an arraylist with all string or line read in the file
     */
    private ArrayList<String> readTextFile(String fullPath) {
        ArrayList<String> list = new ArrayList<>();
        FileText reader = new FileText();
        if (reader.turnOnReader(fullPath)) {
            list = reader.textReader();
            reader.turnOffReader();
        }
        return list;
    }


    /**
     *
     * Load the airplanes data
     */
    private void dataAirplanes() {
        ArrayList<String> list;
        list = readTextFile(fullPathAirplanes);
        if(list.isEmpty()){
            System.out.println("AIRPLANES database is empty.");
            System.out.println("Unavailable service.\nPlease try again later.\nTurning off the AirViseu api ... ");
            System.exit(0);
        }else {
            for (int i = 0; i < list.size(); i++) {
                String aux[] = list.get(i).split(",");
                Airplane airp = new Airplane(aux[0].trim(), Integer.parseInt(aux[1].trim()));
                airplanesList.add(airp);
            }
        }
    }


    /**
     *
     * Load the customers data
     */
    private void dataCustomers() {
        ArrayList<String> list;
        list = readTextFile(fullPathCustomers);
        for (int i = 0; i < list.size(); i++) {
            String aux[] = list.get(i).split(",");
            LocalDate lD = LocalDate.of(Integer.parseInt(aux[4].trim()), Integer.parseInt(aux[5].trim()), Integer.parseInt(aux[6].trim()));
            Customer cust = new Customer(Long.parseLong(aux[0].trim()), aux[1].trim(), aux[2].trim(), aux[3].trim(), lD);
            customersList.add(cust);
        }
    }


    /**
     *
     * Load  the flights data
     */
    private void dataFlights() {
        ArrayList<String> list;
        list = readTextFile(fullPathFlights);
        if(list.isEmpty()){
            System.out.println("FLIGHTS database is empty.");
            System.out.println("Unavailable service.\nPlease try again later.\nTurning off the AirViseu api ... ");
            System.exit(0);
        }else {
            for (int i = 0; i < list.size(); i++) {
                String aux[] = list.get(i).split(",");
                LocalTime lT = LocalTime.of(Integer.parseInt(aux[3].trim()), Integer.parseInt(aux[4].trim()), Integer.parseInt(aux[5].trim()));
                Airplane ap;
                for(int j = 0; j < airplanesList.size(); ++j){
                    if(airplanesList.get(j).getNameAirplane().equals(aux[6].trim())){
                        ap = airplanesList.get(j);
                        Flight fli = new Flight(Integer.parseInt(aux[0].trim()), Integer.parseInt(aux[1].trim()), Flight.DayWeek.valueOf(aux[2].trim()), lT, ap);
                        flightsList.add(fli);
                        break;
                    }
                }
            }
        }
    }


    /**
     *
     * Load the routes data
     */
    private void dataRoutes() {
        ArrayList<String> list;
        list = readTextFile(fullPathRoutes);
        if(list.isEmpty()){
            System.out.println("ROUTES database is empty.");
            System.out.println("Unavailable service.\nPlease try again later.\nTurning off the AirViseu api ... ");
            System.exit(0);
        }else {
            for (int i = 0; i < list.size(); i++) {
                String aux[] = list.get(i).split(",");
                Route rou = new Route(Integer.parseInt(aux[0].trim()), Integer.parseInt(aux[1].trim()), aux[2].trim(), Double.parseDouble(aux[3].trim()));
                routesList.add(rou);
            }
        }
    }


    /**
     *
     * Load the tickets data
     */
    private void dataTickets() {
        ArrayList<String> list = new ArrayList<>();
        list = readTextFile(fullPathTickets);
        for (int i = 0; i < list.size(); i++) {
            String aux[] = list.get(i).split(",");
            LocalDateTime trip = LocalDateTime.of(
                    Integer.parseInt(aux[3].trim()), Integer.parseInt(aux[4].trim()), Integer.parseInt(aux[5].trim()),
                    Integer.parseInt(aux[6].trim()), Integer.parseInt(aux[7].trim()), Integer.parseInt(aux[8].trim()));
            LocalDateTime purchase = LocalDateTime.of(
                    Integer.parseInt(aux[9].trim()), Integer.parseInt(aux[10].trim()), Integer.parseInt(aux[11].trim()),
                    Integer.parseInt(aux[12].trim()), Integer.parseInt(aux[13].trim()), Integer.parseInt(aux[14].trim()));
            Ticket tic = new Ticket(Long.parseLong(aux[0].trim()), Integer.parseInt(aux[1].trim()), Integer.parseInt(aux[2].trim()),
                    trip, purchase,
                    Double.parseDouble(aux[15].trim()), Integer.parseInt(aux[16].trim()));
            ticketsList.add(tic);
        }
    }


    /**
     *
     * Load the prices data
     */
    private void dataPrices() {
        ArrayList<String> list = new ArrayList<>();
        list = readTextFile(fullPathPrices);
        if(list.isEmpty()){
            System.out.println("PRICES database is empty.");
            System.out.println("Unavailable service.\nPlease try again later.\nTurning off the AirViseu api ... ");
            System.exit(0);
        }else{
            Double values[] = new Double[list.size()];

            for (int i = 0; i < list.size(); i++) {
                String aux[] = list.get(i).split(":");
                values[i] = Double.parseDouble(aux[1].trim());
            }
            priceRules = new Prices(values[0], values[1], values[2], values[3], values[4]);
        }
    }




    /**
     *
     * The method open, write and close one text file
     * @param fullPath the full path: the folder name concatenated with the upload file name
     * @param list the list with all string
     */
    public void writerFileText(String fullPath, ArrayList<String> list) {
        FileText writer = new FileText();
        writer.turnOnWriter(fullPath);
        writer.textWriter(list);
        writer.turnOffWriter();
    }




    /**
     *
     * @return airplanesList, the airplane list
     */
    public ArrayList<Airplane> getAirplanesList() {
        return airplanesList;
    }




    /**
     *
     * @return customersList, the customers list
     */
    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }




    /**
     *
     * @return ticketsList, the tickets list
     */
    public ArrayList<Ticket> getTicketsList() {
        return ticketsList;
    }




    /**
     *
     * @return flightsList, the flights list
     */
    public ArrayList<Flight> getFlightsList() {
        return flightsList;
    }




    /**
     *
     * @return routesList, the routes list
     */
    public ArrayList<Route> getRoutesList() {
        return routesList;
    }




    /**
     *
     * @return priceRules, the price rules
     */
    public Prices getPriceRules() {
        return priceRules;
    }




    /**
     *
     * Updateable data in runtime
     * @param customersList the new customers list
     */
    public void setCustomersList(ArrayList<Customer> customersList) {
        this.customersList = customersList;
    }




    /**
     *
     * Updateable data in runtime
     * @param ticketsList the new tickets list
     */
    public void setTicketsList(ArrayList<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }
}
