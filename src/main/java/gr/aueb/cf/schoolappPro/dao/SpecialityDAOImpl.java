package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.model.Speciality;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialityDAOImpl implements ISpecialityDAO{


    @Override
    public Speciality insert(Speciality speciality) throws SpecialityDAOException {
        String sql = "INSERT INTO SPECIALITIES (SPECIALITY) VALUES (?)";
        try (Connection connection  = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, speciality.getSpeciality());

            int n = ps.executeUpdate(); // Execute sql script

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Insert", JOptionPane.PLAIN_MESSAGE);
                return speciality;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality Insert : " + speciality);
        }
    }

    @Override
    public Speciality update(Speciality speciality) throws SpecialityDAOException {
        String sql = "UPDATE SPECIALITIES SET SPECIALITY = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            String specialityName = speciality.getSpeciality();
            int speciality_id = speciality.getId();

            ps.setString(1, specialityName);
            ps.setInt(2, speciality_id);

            int n = ps.executeUpdate();

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Update", JOptionPane.PLAIN_MESSAGE);
                return speciality;
            } else {
                return null;
            }

        } catch (SQLException e ) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL Error in Speciality Update:" + speciality);
        }
    }

    @Override
    public void delete(int id) throws SpecialityDAOException {
        String sql = "DELETE FROM SPECIALITIES WHERE ID = ?";

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
            throw new SpecialityDAOException("SQL Error in Speciality Delete with id: " + id);
        }
    }

    @Override
    public List<Speciality> getBySpeciality(String speciality) throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES WHERE SPECIALITY = ?";
        List<Speciality> specialities = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, speciality);
            rs = ps.executeQuery();

            while (rs.next()) {
                Speciality specialityNew = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialities.add(specialityNew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL ERROR in SPECIALITY Select with speciality : " + speciality);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return specialities;
    }

    @Override
    public Speciality getById(int id) throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES WHERE ID = ?";
        Speciality speciality = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                return speciality;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL ERROR in Speciality Select with id : " + id);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return speciality;
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        String sql = "SELECT * FROM SPECIALITIES";
        Set<Speciality> specialities = new HashSet<>(); // Set so we remove duplicates
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                Speciality speciality = new Speciality(rs.getInt("ID"), rs.getString("SPECIALITY"));
                specialities.add(speciality);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SpecialityDAOException("SQL ERROR in Getting all the Specialities");
        }
        return new ArrayList<>(specialities);
    }
}
