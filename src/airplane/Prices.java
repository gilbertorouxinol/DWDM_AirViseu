package airplane;

import java.text.DecimalFormat;

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
 * Class for calculate the flight pricing<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class Prices {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private double pricePerKilometer;
    private double lowerLimitDiscountPercentage;
    private double upperLimitDiscountPercentage;
    private double lowerLimitDiscountCost;
    private double upperLimitDiscountCost;

    /**
     *
     * @param pricePerKilometer the price per kilometer
     * @param lowerLimitDiscountCost minimum amount in euros to get the first category of discount
     * @param lowerLimitDiscountPercentage the lower limit of the percentage discount
     * @param upperLimitDiscountCost maximum amount in euros to get the second category of discount
     * @param upperLimitDiscountPercentage the upper limit of the percentage discount
     */
    public Prices(double pricePerKilometer,
                  double lowerLimitDiscountCost,
                  double lowerLimitDiscountPercentage,
                  double upperLimitDiscountCost,
                  double upperLimitDiscountPercentage) {
        this.pricePerKilometer = pricePerKilometer;
        this.lowerLimitDiscountCost = lowerLimitDiscountCost;
        this.lowerLimitDiscountPercentage = lowerLimitDiscountPercentage;
        this.upperLimitDiscountCost = upperLimitDiscountCost;
        this.upperLimitDiscountPercentage = upperLimitDiscountPercentage;
    }

    /**
     *
     * @param km the distance
     * @return priceTrip, the price trip value with 2 decimal places
     */
    public double makePriceTrip(double km){
        double priceTrip;
        double aux = km*pricePerKilometer;
        if( aux < lowerLimitDiscountCost)
            priceTrip = Double.parseDouble(df.format(aux));
        else if( aux < upperLimitDiscountCost)
            priceTrip = Double.parseDouble(df.format(aux*(1.0-lowerLimitDiscountPercentage/100.0)));
        else
            priceTrip = Double.parseDouble(df.format(aux*(1.0-upperLimitDiscountPercentage/100.0)));
        return priceTrip;
    }
}
