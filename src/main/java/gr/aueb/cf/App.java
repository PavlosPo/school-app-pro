package gr.aueb.cf;

import gr.aueb.cf.schoolappPro.dao.*;
import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappPro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappPro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolappPro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappPro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappPro.dto.UserInsertDTO;
import gr.aueb.cf.schoolappPro.model.Teacher;
import gr.aueb.cf.schoolappPro.service.*;
import gr.aueb.cf.schoolappPro.service.exceptions.TeacherNotFoundException;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {

        insertATeacher();

    }

    public static void insertATeacher() {
        ITeacherDAO teacherDAO = new TeacherDAOImpl();
        ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

        // DTO
        TeacherInsertDTO teacherInsertDTO = new TeacherInsertDTO();
        teacherInsertDTO.setSsn(1234);
        teacherInsertDTO.setFirstname("PavlosTeacher");
        teacherInsertDTO.setLastname("Poulos");
        teacherInsertDTO.setSpecialityId(1);
        teacherInsertDTO.setUserId(1);


        try {
            teacherService.insertTeacher(teacherInsertDTO);
            System.out.println("Succesfully inserted teacher : " + teacherInsertDTO);
            System.out.println(teacherDAO.getAllTeachers());
        } catch (TeacherDAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertASpeciality() {
        ISpecialityDAO specialityDAO = new SpecialityDAOImpl();
        ISpecialitiesService specialitiesService = new SpecialityServiceImpl(specialityDAO);

        // DTO
        SpecialityInsertDTO specialityInsertDTO = new SpecialityInsertDTO();
        specialityInsertDTO.setSpeciality("Mathematician");

        // MAP

        try {
            specialitiesService.insertSpeciality(specialityInsertDTO);
        } catch (SpecialityDAOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertAUser() {
        IUserDAO userDAO = new UserDAOImpl();
        IUserService userService = new UserServiceImpl(userDAO);

        // DTO
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setUsername("username1");
        userInsertDTO.setPassword("1234");

        // insert
        try {
            userService.insertUser(userInsertDTO);
        } catch (UserDAOException e) {
            System.out.println(e.getMessage());
        }
    }
}
