package airplane;

import java.time.LocalDateTime;

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
 * Class to represent a ticket<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Ticket {
    private long customerID;
    private int idRoute;
    private int idFlight;
    private LocalDateTime dateTrip;
    private LocalDateTime dataPurchase;
    private double price;
    private int jockey;

    /**
     *
     * @param customerID the customer ID
     * @param idRoute the route ID
     * @param idFlight the flght ID
     * @param dateTrip the trip date and time
     * @param dataPurchase the purchase date and time
     * @param price the flight price
     * @param jockey the ticket status (0 - delete ticket | 1 - effective ticket | 2 - booking ticket)
     */
    public Ticket(long customerID,
                  int idRoute,
                  int idFlight,
                  LocalDateTime dateTrip,
                  LocalDateTime dataPurchase,
                  double price,
                  int jockey) {
        this.customerID = customerID;
        this.idRoute = idRoute;
        this.idFlight = idFlight;
        this.dateTrip = dateTrip;
        this.dataPurchase = dataPurchase;
        this.price = price;
        this.jockey = jockey;
    }

    /**
     *
     * @return customerID, the customer ID
     */
    public long getCustomerID() {
        return customerID;
    }

    /**
     *
     * @return idRoute, the route ID
     */
    public int getIdRoute() {
        return idRoute;
    }

    /**
     *
     * @return idFlight, the flght ID
     */
    public int getIdFlight() {
        return idFlight;
    }

    /**
     *
     * @return dateTrip, the trip date and time
     */
    public LocalDateTime getDateTrip() {
        return dateTrip;
    }

    /**
     *
     * @return dataPurchase, the purchase date and time
     */
    public LocalDateTime getDataPurchase() {
        return dataPurchase;
    }

    /**
     *
     * @return price, the flight price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return jockey, the ticket status (0 - delete ticket | 1 - effective ticket | 2 - booking ticket)
     */
    public int getJockey() {
        return jockey;
    }

    /**
     *
     * @param jockey the ticket status (0 - delete ticket | 1 - effective ticket | 2 - booking ticket)
     */
    public void setJockey(int jockey) {
        this.jockey = jockey;
    }
}
