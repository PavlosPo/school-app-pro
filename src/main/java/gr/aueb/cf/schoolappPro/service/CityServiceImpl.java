package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.ICityDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolappPro.dto.CityInsertDTO;
import gr.aueb.cf.schoolappPro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolappPro.model.City;
import gr.aueb.cf.schoolappPro.service.exceptions.CityNotFoundException;

import java.util.List;

public class CityServiceImpl implements ICityService{
    private final ICityDAO dao;

    public CityServiceImpl(ICityDAO dao) {
        this.dao = dao;
    }

    @Override
    public City insertCity(CityInsertDTO cityInsertDTO) throws CityDAOException {
        City city = mapCityFromCityInsertDTO(cityInsertDTO);
        return dao.insert(city);
    }

    @Override
    public City updateCity(CityUpdateDTO cityUpdateDTO) throws CityDAOException, CityNotFoundException {
        City city = mapCityFromCityUpdateDTO(cityUpdateDTO);
        return dao.update(city);
    }

    @Override
    public void deleteCity(int id) throws CityDAOException, CityNotFoundException {
        if (!dao.cityExistsById(id)) {
            throw new CityNotFoundException("City with id : " + id + " not found");
        }
        dao.delete(id);
    }

    @Override
    public City getCity(String cityStr) throws CityDAOException, CityNotFoundException {
        if (!dao.cityExistsByCityName(cityStr)) {
            throw new CityNotFoundException("City with name : " + cityStr + " not found");
        }
        return dao.getByCity(cityStr).get(0);
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        return dao.getAllCities();
    }

    private City mapCityFromCityInsertDTO(CityInsertDTO cityInsertDTO) {
        return new City(null, cityInsertDTO.getCity());
    }

    private City mapCityFromCityUpdateDTO(CityUpdateDTO cityUpdateDTO) {
        return new City(cityUpdateDTO.getId(), cityUpdateDTO.getCity());
    }
}
