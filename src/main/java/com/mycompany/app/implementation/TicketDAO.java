package com.mycompany.app.implementation;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.app.airport.Flight;
import com.mycompany.app.airport.Seat;
import com.mycompany.app.airport.Ticket;
import com.mycompany.app.exception.FlightException;
import com.mycompany.app.exception.TicketException;
import com.mycompany.app.interfaces.IFlightDAO;
import com.mycompany.app.interfaces.ITicketDAO;
import com.mycompany.app.utils.Model;

public class TicketDAO implements ITicketDAO {

    private static Model model;
    private static ITicketDAO ticketDAO;
    private static IFlightDAO flightDAO;

    private TicketDAO() {
        model = Model.getInstance();
    }

    public static ITicketDAO getInstance() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAO();
            flightDAO = FlightDAO.getInstance();
        }
        return ticketDAO;
    }

    @Override
    public Ticket buyFlightTicket(Flight flight, Seat seat) throws TicketException {
        if(flight.getPrice()<model.getPassenger().getMoney()){
            Ticket newTicket = new Ticket();
            newTicket.setFlight(flight);
            newTicket.setSeat(seat);
            Ticket ticketSold = model.getPassenger().addTicket(newTicket);
            if (ticketSold!=null) {
                model.getPassenger().buyTicket(flight.getPrice());
                return ticketSold;
            }
        }
        throw new TicketException("Passenger doesn`t have enough money");
    }

    @Override
    public List<Ticket> buyRouteTickets(List<Flight> flights, List<Seat> seats) throws TicketException {
        List<Ticket> tickets = new ArrayList<>();
        try{
            if(flightDAO.getRoutePrice(flights)>model.getPassenger().getMoney()){
                throw new TicketException("Passenger doesn`t have enough money");
            }
            for(int i=0; i<flights.size(); i++){
                Ticket ticket = buyFlightTicket(flights.get(i),seats.get(i));
                tickets.add(ticket);
            }
        }catch (FlightException e){
            throw new TicketException(e.getMessage());
        }
        return tickets;
    }
}
