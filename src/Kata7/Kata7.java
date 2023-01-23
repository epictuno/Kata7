package kata7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;

public class Kata7 {

    public static void main(String[] args) {
        FlightsApp App = new FlightsApp();

        get("/flights/all", (req, res) -> {
            return App.getAll();
        });

        get("/flights/day/:day", (req, res) -> {
            return App.getFlightsOnDay(req.params(":day"));
        });

        get("/flights/distance/bigger/:distance", (req, res) -> {
            return App.getHigherDistanceFlights(req.params("distance"));
        });

        get("/flights/distance/lower/:distance", (req, res) -> {
            return App.getLowerDistanceFlights(req.params(":distance"));
        });

        get("/flights/cancelled", (req, res) -> {
            return App.getCancelledFlights();
        });

        get("/flights/diverted", (req, res) -> {
            return App.getDivertedFlights();
        });

    }
}
