package airplane;

import java.time.LocalTime;

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
 * Class to represent a flight<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Flight {

    public enum DayWeek {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday}

    private int idRoute;
    private int idFlight;
    private DayWeek day;
    private LocalTime time;
    private Airplane airp;

    /**
     *
     * @param idRoute the route ID
     * @param idFlight the flight ID
     * @param day the flight day
     * @param time the flight time
     * @param airp the plane of the flight
     */
    public Flight(int idRoute,
                  int idFlight,
                  DayWeek day,
                  LocalTime time,
                  Airplane airp) {
        this.idRoute = idRoute;
        this.idFlight = idFlight;
        this.day = day;
        this.time = time;
        this.airp = airp;
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
     * @return idFlight, the flight ID
     */
    public int getIdFlight() {
        return idFlight;
    }

    /**
     *
     * @return day, the flight day
     */
    public DayWeek getDay() {
        return day;
    }

    /**
     *
     * @return time, the flight time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     *
     * @return airp, the plane of the flight
     */
    public Airplane getAirp() {
        return airp;
    }
}
