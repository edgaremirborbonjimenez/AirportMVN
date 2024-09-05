package com.mycompany.app.implementation;


import java.util.Collection;
import java.util.List;

import com.mycompany.app.airport.Airport;
import com.mycompany.app.interfaces.IAirportDAO;
import com.mycompany.app.utils.Model;

public class AirportDAO implements IAirportDAO {

    private static IAirportDAO airportDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;

        private AirportDAO(){
            model = Model.getInstance();
        }

        public static IAirportDAO getInstance(){
            if(airportDAO==null){
                airportDAO = new AirportDAO();
            }
            return airportDAO;
        }


    @Override
    public void setAirport(Airport airport){
            model.setAirport(airport);
    }

}
