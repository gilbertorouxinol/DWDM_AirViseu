package airplane;

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
 * Class to represent a route<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Route {
    private int idRoute;
    private int maxFlight;
    private String destination;
    private double distance;

    /**
     *
     * @param idRoute route ID
     * @param maxFlight number of flights of this route
     * @param destination flight destination
     * @param distance flight distance
     */
    public Route(int idRoute,
                 int maxFlight,
                 String destination,
                 double distance) {
        this.idRoute = idRoute;
        this.maxFlight = maxFlight;
        this.destination = destination;
        this.distance = distance;
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
     * @return maxFlight, the route number of flights
     */
    public int getMaxFlight() {
        return maxFlight;
    }

    /**
     *
     * @return destination, the flight destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @return distance, the flight distance
     */
    public double getDistance() {
        return distance;
    }
}
