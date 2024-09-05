package com.mycompany.app.implementation;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.List;

import com.mycompany.app.airport.City;
import com.mycompany.app.exception.AirportException;
import com.mycompany.app.interfaces.ICityDAO;
import com.mycompany.app.utils.Model;

public class CityDAO implements ICityDAO {

    //private static final Logger logger = LogManager.getLogger("Airport");

    private static ICityDAO cityDAO;
    private static final int numRec = 0;
    private static final int count = 4;
    private static Model model;


    private CityDAO() {
        model = Model.getInstance();
    }

    public static ICityDAO getInstance() {
        if (cityDAO == null) {
            cityDAO = new CityDAO();
        }
        return cityDAO;
    }

    @Override
    public List<City> getCities() throws Exception {
        List<City> cities = model.getCities();

        if(cities==null||cities.size()==0){
            //logger.error("There is No City Available");
            throw new AirportException("There is No City Available");
        }
        return cities;    }

    @Override
    public void setCities(List<City> cities) {
        model.setCities(cities);
    }
}
