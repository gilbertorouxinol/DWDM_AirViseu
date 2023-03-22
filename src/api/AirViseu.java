package api;

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
 * Class that represent the entire API and launch the view object which has the main()<br><br>
 * <p>
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 */
public class AirViseu {
    public static void main(String[] args) {




        // -------------------------------------------------------------------------------------------------------------
        // Model - Object m, that load all data
        // -------------------------------------------------------------------------------------------------------------
        // Data folder - the entire main path name
        String folder = "/Users/gr/Documents/Projetos Java/DWDM AirViseu/textFiles/";

        // Indispensable data (use the constructor with 6 parameters):
        String fileNameAirplane = folder + "airplanes.txt";
        String fileNameFlight = folder + "flights.txt";
        String fileNameRoute = folder + "routes.txt";
        String fileNamePrices = folder + "prices.txt";

        // Dispensable data (use the constructor with 4 parameters):
        String fileNameCustomer = folder + "customers.txt";
        String fileNameTicket = folder + "tickets.txt";

        // Constructor with 6 parameter - There is also a constructor only with the first 4 parameters
        Model m = new Model(fileNameAirplane, fileNameFlight, fileNameRoute, fileNamePrices, fileNameCustomer, fileNameTicket);




        // -------------------------------------------------------------------------------------------------------------
        // Controler - Object c, that responds to all requests makes in the view (menu)
        // -------------------------------------------------------------------------------------------------------------
        Controller c = new Controller(m, folder);




        // -------------------------------------------------------------------------------------------------------------
        // View - Object v, to make all orders - it's the menu (> java -jar nome.jar)
        // -------------------------------------------------------------------------------------------------------------
        View v = new View(c);
        v.launch();
    }
}
