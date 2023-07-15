package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.IStudentDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolappPro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolappPro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Student;
import gr.aueb.cf.schoolappPro.service.exceptions.StudentNotFoundException;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private IStudentDAO dao;

    public StudentServiceImpl(IStudentDAO dao) {
        this.dao = dao;
    }

    /**
     * It inserts a student from a {@link StudentInsertDTO} instance
     * @param dto   the dto instance
     * @return  the student created
     * @throws StudentDAOException  if the insert has problems in the DAO layer
     */
    @Override
    public Student insertStudent(StudentInsertDTO dto) throws StudentDAOException {
        Student student = mapStudentFromStudentInsertDTO(dto);
        dao.insert(student);
        return student;
    }

    @Override
    public Student updateStudent(StudentUpdateDTO dto) throws StudentDAOException, StudentNotFoundException {
        return mapStudentFromStudentUpdateDTO(dto);
    }


    @Override
    public void deleteStudent(int id) throws StudentDAOException, StudentNotFoundException {
        if (!dao.studentIdExists(id)) {
            throw new StudentNotFoundException("Student with id : " + id + " not exists");
        }
        dao.delete(id);

    }

    @Override
    public Student getStudentById(int id) throws StudentDAOException, StudentNotFoundException {
        if (!dao.studentIdExists(id)) {
            throw new StudentNotFoundException("Student with id : " + id + " not exists");
        }
        return dao.getById(id);
    }


    @Override
    public List<Student> getStudentByFirstname(String firstname) throws StudentDAOException {
        return dao.getByFirstname(firstname);
    }

    @Override
    public List<Student> getStudentByLastname(String lastname) throws StudentDAOException {
        return dao.getByLastname(lastname);
    }

    private Student mapStudentFromStudentInsertDTO(StudentInsertDTO studentInsertDTO) {
        return new Student(null, studentInsertDTO.getFirstname(),
                studentInsertDTO.getLastname(),
                studentInsertDTO.getGender(),
                studentInsertDTO.getDateOfBirth(),
                studentInsertDTO.getCityId(),
                studentInsertDTO.getUserId());
    }

    private Student mapStudentFromStudentUpdateDTO(StudentUpdateDTO studentUpdateDTO) {
        return new Student(studentUpdateDTO.getId(), studentUpdateDTO.getFirstname(),
                studentUpdateDTO.getLastname(),
                studentUpdateDTO.getGender(),
                studentUpdateDTO.getDateOfBirth(),
                studentUpdateDTO.getCityId(),
                studentUpdateDTO.getUserId());
    }
}
