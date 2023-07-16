package gr.aueb.cf.schoolappPro.dao;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import gr.aueb.cf.schoolappPro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappPro.model.Teacher;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO{

    public TeacherDAOImpl() {}


    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (SSN, FIRSTNAME, LASTNAME, SPECIALITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            Integer ssn = teacher.getSsn();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();
            Integer specialityId = teacher.getSpecialityId();
            Integer userId = teacher.getUserId();

            ps.setInt(1, ssn);
            ps.setString(2, firstname); // sql injection protection
            ps.setString(3, lastname);
            ps.setInt(4, specialityId);
            ps.setInt(5, userId);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Insert", JOptionPane.PLAIN_MESSAGE);
                return teacher;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher Insert" + teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ? , LASTNAME = ?, USER_ID = ?  WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = teacher.getId();
            Integer ssn = teacher.getSsn();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();


            ps.setString(1, firstname); // sql injection protection
            ps.setString(2, lastname);
            ps.setInt(3, ssn);
            ps.setInt(4, id);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Update", JOptionPane.PLAIN_MESSAGE);
                return teacher;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher Update " + teacher);
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);

            int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος/η ?", "Warning", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int n = ps.executeUpdate();
                if (n >= 1) {
                    JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Delete", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rows affected", "Delete", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL ERROR in Teacher Delete with id:  " + id);
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME = ?";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, lastname);
            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("SSN"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                        rs.getInt("SPECIALITY_ID"), rs.getInt("USER_ID"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL ERROR in Teacher Select with lastname : " + lastname);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teachers;
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("SSN"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                        rs.getInt("SPECIALITY_ID"), rs.getInt("USER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TeacherDAOException("SQL ERROR in Teacher Select with id : " + id);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM TEACHERS";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("SSN"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                        rs.getInt("SPECIALITY_ID"), rs.getInt("USER_ID"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}
