package com.mycompany.app.interfaces;

import java.util.List;

import com.mycompany.app.airport.Flight;
import com.mycompany.app.airport.Seat;
import com.mycompany.app.airport.Ticket;

public interface ITicketDAO {
    Ticket buyFlightTicket(Flight flight,Seat seat);
    List<Ticket> buyRouteTickets(List<Flight> flights, List<Seat> seats);

}
