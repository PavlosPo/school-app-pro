package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolappPro.model.Speciality;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;
import gr.aueb.cf.schoolappPro.service.util.DateUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SpecialityDAOImpl implements ISpecialityDAO{


    @Override
    public Speciality insert(Speciality speciality) throws SpecialityDAOException {
        String sql = "INSERT INTO SPECIALITIES (SPECIALITY) VALUES (?)";
        try (Connection connection  = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {



            int n = ps.executeUpdate(); // Execute sql script

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Insert", JOptionPane.PLAIN_MESSAGE);
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student Insert Student: " + student);
        }
    }

    @Override
    public Speciality update(Speciality speciality) throws SpecialityDAOException {
        return null;
    }

    @Override
    public void delete(Speciality speciality) throws SpecialityDAOException {

    }

    @Override
    public List<Speciality> getBySpeciality(String speciality) throws SpecialityDAOException {
        return null;
    }

    @Override
    public Speciality getById(Speciality speciality) throws SpecialityDAOException {
        return null;
    }
}
