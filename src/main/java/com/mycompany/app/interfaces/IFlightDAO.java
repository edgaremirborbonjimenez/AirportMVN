package com.mycompany.app.interfaces;

import java.util.List;

import com.mycompany.app.airport.City;
import com.mycompany.app.airport.Flight;
import com.mycompany.app.exception.FlightException;

public interface IFlightDAO {
    public List<List<Flight>> getRoute(City leaving, City goingTo)throws FlightException;
    public double getRoutePrice(List<Flight> flights)throws FlightException;

}
