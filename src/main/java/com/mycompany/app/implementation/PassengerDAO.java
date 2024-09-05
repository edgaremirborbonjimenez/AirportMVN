package com.mycompany.app.implementation;

import com.mycompany.app.interfaces.IPassengerDAO;
import com.mycompany.app.people.Passenger;
import com.mycompany.app.utils.Model;

public class PassengerDAO implements IPassengerDAO {
    private static Model model;
    private static IPassengerDAO passengerDAO;

    private PassengerDAO() {
        model = Model.getInstance();
    }

    public static IPassengerDAO getInstance() {
        if (passengerDAO == null) {
            passengerDAO = new PassengerDAO();
        }
        return passengerDAO;
    }

    @Override
    public Passenger getPassegerInfo() {
        return model.getPassenger();
    }
}
