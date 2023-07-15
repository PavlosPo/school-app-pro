package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolappPro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolappPro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Student;
import gr.aueb.cf.schoolappPro.service.exceptions.StudentNotFoundException;

import java.util.List;

public interface IStudentService {

    Student insertStudent(StudentInsertDTO dto) throws StudentDAOException;
    Student updateStudent(StudentUpdateDTO dto) throws StudentDAOException, StudentNotFoundException;
    void deleteStudent(int id) throws StudentDAOException, StudentNotFoundException;
    Student getStudentById(int id) throws StudentDAOException, StudentNotFoundException;
    List<Student> getStudentByFirstname(String firstname) throws StudentDAOException,StudentNotFoundException;
    List<Student> getStudentByLastname(String lastname) throws StudentDAOException, StudentNotFoundException;
}
