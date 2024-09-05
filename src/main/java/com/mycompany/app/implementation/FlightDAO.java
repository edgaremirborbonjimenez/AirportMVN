package com.mycompany.app.implementation;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.List;

import com.mycompany.app.airport.Airport;
import com.mycompany.app.airport.City;
import com.mycompany.app.airport.Flight;
import com.mycompany.app.exception.FlightException;
import com.mycompany.app.interfaces.IFlightDAO;
import com.mycompany.app.utils.Model;

public class FlightDAO implements IFlightDAO {
    //private static final Logger logger = LogManager.getLogger("Airport");

    private static IFlightDAO flightDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;

    private FlightDAO() {
        model = Model.getInstance();
    }

    public static IFlightDAO getInstance(){
        if(flightDAO == null){
            flightDAO = new FlightDAO();
        }
        return flightDAO;
    }

    @Override
    public List<List<Flight>> getRoute(City leaving, City goingTo) throws FlightException{
        Airport airport = model.getAirport();
        if(airport == null){
            //logger.error("There is No Airport Available");
            throw new FlightException("There is No Airport Available");
        }
        return airport.getRoute(leaving,goingTo,count,numRec);    }

    @Override
    public double getRoutePrice(List<Flight> flights) throws FlightException {
        Airport airport = model.getAirport();

        if (flights == null || flights.size() == 0) {
            //logger.error("There is No Flight Available");
            throw new FlightException("There is No Flight Available");
        }
        return airport.getRoutePrice(flights);
    }
}
