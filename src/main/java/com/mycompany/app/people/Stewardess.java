package com.mycompany.app.people;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import com.mycompany.app.airport.Airplane;
import com.mycompany.app.interfaces.IPassenger;

public class Stewardess extends AirplaneCrew implements IPassenger {

    Collection<String> personalStrengths;


    public Stewardess() {
        
    }

    @Override
    public void anounceMessage() {
        System.out.println("There is any help you need?");
    }

    public Stewardess(String firstName, String lastName, Date birthDate, Date dateStartedWorking,Collection<String> personalStrengths, Airplane airplane) {
        super(firstName, lastName, birthDate, dateStartedWorking,airplane);
        this.personalStrengths = personalStrengths;
    }
    public Collection<String> getPersonalStrengths() {
        return personalStrengths;
    }

    public void setPersonalStrengths(Collection<String> personalStrengths) {
        this.personalStrengths = personalStrengths;
    }

    @Override
    public String toString() {
        return "Stewardess{" +
                "personalStrengths=" + personalStrengths.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stewardess that = (Stewardess) o;
        return Objects.deepEquals(personalStrengths, that.personalStrengths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personalStrengths);
    }

    @Override
    public void saySomething() {
        System.out.println("Please stay sit, if you need help just ask me");
    }
}
