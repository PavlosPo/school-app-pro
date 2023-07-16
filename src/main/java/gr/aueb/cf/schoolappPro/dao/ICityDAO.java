package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolappPro.model.City;

import java.util.List;

public interface ICityDAO {
    City insert(City city) throws CityDAOException;
    City update(City city) throws CityDAOException;
    void delete(int id) throws CityDAOException;
    List<City> getById(int id) throws CityDAOException;
    List<City> getByCity(String cityName) throws CityDAOException;

    List<City> getAllCities() throws CityDAOException;

    boolean cityExistsById(int id) throws CityDAOException;
    boolean cityExistsByCityName(String city) throws CityDAOException;
}
