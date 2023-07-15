package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolappPro.model.City;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {

    public CityDAOImpl() {}

    @Override
    public City insert(City city) throws CityDAOException {
        String sql = "INSERT INTO CITIES (CITY) VALUES (?)";
        try (Connection connection  = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String city_name = city.getCity();
            ps.setString(1, city_name);
            int n = ps.executeUpdate(); // Execute sql script
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Insert", JOptionPane.PLAIN_MESSAGE);
                return city;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in City Insert City: " + city);
        }
    }

    @Override
    public City update(City city) throws CityDAOException {
        String sql = "UPDATE CITIES SET CITY = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String city_name = city.getCity();
            Integer city_id = city.getId();

            int n = ps.executeUpdate();

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Update", JOptionPane.PLAIN_MESSAGE);
                return city;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in Cities Update city with id: " + city.getId());
        }
    }

    @Override
    public void delete(int id) throws CityDAOException {
        String sql = "DELETE FROM CITIES WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος/η?", "Warning", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int n = ps.executeUpdate();
                if (n >= 1) {
                    JOptionPane.showMessageDialog(null, n +" rows affected", "Delete", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rows affected", "Delete", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL Error in Cities Delete with id: " + id);
        }
    }

    @Override
    public List<City> getById(int id) throws CityDAOException {
        String sql = "SELECT * FROM CITES WHERE ID = ?";
        List<City> cities = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                City city = new City(rs.getInt("ID"), rs.getString("CITY"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL ERROR in Cities Select with id : " + id);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }

    @Override
    public List<City> getByCity(String cityName) throws CityDAOException {
        String sql = "SELECT * FROM CITIES WHERE CITY LIKE ?";
        List<City> cities = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, cityName + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                City city = new City(rs.getInt("ID"), rs.getString("CITY"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CityDAOException("SQL ERROR in Cities Select with name : " + cityName);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }
}
