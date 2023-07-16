package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolappPro.dto.CityInsertDTO;
import gr.aueb.cf.schoolappPro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolappPro.model.City;
import gr.aueb.cf.schoolappPro.service.exceptions.CityNotFoundException;

import java.util.List;

public interface ICityService {

    City insertCity(CityInsertDTO cityInsertDTO) throws CityDAOException;
    City updateCity(CityUpdateDTO cityUpdateDTO) throws CityDAOException, CityNotFoundException;
    void deleteCity(int id) throws CityDAOException, CityNotFoundException;
    City getCity(String cityStr) throws CityDAOException, CityNotFoundException;
    List<City> getAllCities() throws CityDAOException;


}
