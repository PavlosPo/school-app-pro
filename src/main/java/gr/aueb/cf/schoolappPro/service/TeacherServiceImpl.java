package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.ITeacherDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappPro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappPro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Teacher;
import gr.aueb.cf.schoolappPro.service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService{
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        if (dto == null) return null;
        Teacher teacher;
        try {
            teacher = map(dto);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException {
        if (dto == null) return null;
        Teacher teacher;
        try {
            teacher = map(dto);
            if (teacherDAO.getById(teacher.getId()) == null) {
                throw new TeacherNotFoundException(teacher);
            }
            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherDAO.getById(id);
            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher with id: " + id + " not found");
            }
            teacherDAO.delete(teacher.getId());
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers;
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(int id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;

        try {
            teacher = teacherDAO.getById(id);
            if (teacher == null) {
                throw new TeacherNotFoundException("Search Error: Teacher with id: " + id + " was not found");
            }
            return teacher;
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Teacher map(TeacherInsertDTO dto) {
        return new Teacher(dto.getSsn() , dto.getFirstname(), dto.getLastname(), dto.getSpecialityId(), dto.getUserId());    // null as id
    }

    private Teacher map(TeacherUpdateDTO dto) {
        return new Teacher(dto.getSsn() , dto.getFirstname(), dto.getLastname(), dto.getSpecialityId(), dto.getUserId());
    }
}
