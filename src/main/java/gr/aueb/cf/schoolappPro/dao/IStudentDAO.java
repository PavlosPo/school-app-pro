package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolappPro.model.Student;

import java.util.List;

public interface IStudentDAO {
    Student insert(Student student) throws StudentDAOException;
    Student update(Student student) throws StudentDAOException;
    void delete(int id) throws StudentDAOException;

    List<Student> getByFirstname(String firstname) throws StudentDAOException;
    List<Student> getByLastname(String lastname) throws StudentDAOException;
    Student getById(int id) throws StudentDAOException;
    Boolean studentIdExists(int id) throws StudentDAOException;
    List<Student> getAllStudents();
    Boolean studentExists(Student student) throws StudentDAOException;
}
