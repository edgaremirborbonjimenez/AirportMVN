package com.mycompany.app.interfaces;

import java.util.List;

import com.mycompany.app.airport.City;

public interface ICityDAO {
    public List<City> getCities()throws Exception;
    public void setCities(List<City> cities);
}
