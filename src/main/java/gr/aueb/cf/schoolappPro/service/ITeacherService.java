package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappPro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappPro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Teacher;
import gr.aueb.cf.schoolappPro.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {

    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException;
    void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException;
    Teacher getTeacherById(int id) throws TeacherDAOException, TeacherNotFoundException;
}

// NOTE: service layer we throw mostly logical exception.
