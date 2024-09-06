package com.mycompany.app.people;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.mycompany.app.airport.Ticket;
import com.mycompany.app.exception.TicketException;

public class Passenger extends Person {

    private String passportCode;
    private Collection<Ticket> ticket;
    private double money;

    public Passenger() {
        ticket = new ArrayList();
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public Collection<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Collection<Ticket> ticket) {
        this.ticket = ticket;
    }

    public double getMoney() {
        return money;
    }

    public double buyTicket(double price)throws TicketException {
        if (money <= price) {
            throw new TicketException("Passenget doesn´t have enough money");
        }
        money -= price;
        return money;
    }

    public Ticket addTicket(Ticket ticket) {
         this.ticket.add(ticket);
         return ticket;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passportCode='" + passportCode + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(passportCode, passenger.passportCode) && Objects.equals(ticket, passenger.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportCode, ticket);
    }
}


