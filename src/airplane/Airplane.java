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
 * Class to represent an airplane<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 * @use
 */
public class Airplane {

    private int numberSeats;
    private String nameAirplane;

    /**
     *
     * @param nameAirplane the airplane name
     * @param numberSeats the maximum number of passengers on the plane
     */
    public Airplane(String nameAirplane, int numberSeats){
        this.nameAirplane = nameAirplane;
        this.numberSeats = numberSeats;
    }

    /**
     *
     * @return numberSeats, the number of seats
     */
    public int getNumberSeats() {
        return numberSeats;
    }

    /**
     *
     * @return nameAirplane, the airplane name
     */
    public String getNameAirplane() {
        return nameAirplane;
    }
}
