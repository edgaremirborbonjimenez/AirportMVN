package com.mycompany.app.interfaces;

import java.util.List;

import com.mycompany.app.airport.Flight;
import com.mycompany.app.airport.Seat;

public interface ISeatDAO {
    Seat asignSeat(Flight flight);
    List<Seat> getSeatsByFlight(Flight flight);
}
