package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolappPro.model.Student;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;
import gr.aueb.cf.schoolappPro.service.util.DateUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO{


    public StudentDAOImpl() {}

    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, GENDER, BIRTH_DATE, CITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection  = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            String firstname = student.getFirstname();
            String lastname = student.getLastname();
            String gender = student.getGender();

            // Cast Date to sql type
            Date birth_date_javaType = student.getBirthDate();
            java.sql.Date birth_date_sqlType = DateUtil.toSQLDate(birth_date_javaType);

            Integer city_id = student.getCityId();
            Integer user_id = student.getUserId();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, gender);
            ps.setDate(4, birth_date_sqlType);
            ps.setInt(5, city_id);
            ps.setInt(6, user_id);

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
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, CITY_ID = ? , USER_ID = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            String firstname = student.getFirstname();
            String lastname = student.getLastname();
            Integer city_id = student.getCityId();
            Integer user_id = student.getUserId();
            int id = student.getId();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, city_id);
            ps.setInt(4, user_id);
            ps.setInt(5, id);

            int n = ps.executeUpdate();

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Update", JOptionPane.PLAIN_MESSAGE);
                return student;
            } else {
                return null;
            }

        } catch (SQLException e ) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Students Update");
        }
    }

    @Override
    public void delete(int id) throws StudentDAOException {
        String sql = "DELETE FROM STUDENTS WHERE ID = ?";

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
            throw new StudentDAOException("SQL Error in Teacher Delete with id: " + id);
        }
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME = ?";
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, lastname);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("GENDER"), rs.getDate(rs.getRow()), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL ERROR in STUDENT Select with lastname : " + lastname);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    @Override
    public List<Student> getByFirstname(String firstname) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE FIRSTNAME = ?";
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, firstname);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("GENDER"), rs.getDate(rs.getRow()), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL ERROR in STUDENT Select with lastname : " + firstname);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    @Override
    public Student getById(int id) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE ID = ?";
        Student student = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("GENDER"), rs.getDate(rs.getRow()), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL ERROR in Student Select with id : " + id);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM STUDENTS";
        List<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("GENDER"), rs.getDate("BIRTH_DATE"), rs.getInt("CITY_ID"), rs.getInt("USER_ID"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Boolean studentIdExists(int id) throws StudentDAOException {
         List<Student> students = getAllStudents();
         for (Student student : students) {
             if(student.getId() == id) {
                 return true;
             }
         }
         return false;
    }

    @Override
    public Boolean studentExists(Student student) throws StudentDAOException {
        List<Student> students = getAllStudents();
        for (Student currentStudent : students) {
            if(currentStudent.getId() == student.getId()) {
                return true;
            }
        }
        return false;
    }
}
