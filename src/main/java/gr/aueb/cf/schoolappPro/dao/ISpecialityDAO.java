package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.model.Speciality;

import java.util.List;

public interface ISpecialityDAO {
    Speciality insert(Speciality speciality) throws SpecialityDAOException;
    Speciality update(Speciality speciality) throws SpecialityDAOException;
    void delete(int id) throws SpecialityDAOException;
    Speciality getBySpeciality(String speciality) throws SpecialityDAOException;
    Speciality getById(int id) throws SpecialityDAOException;
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;

    boolean specialityIdExists(int id) throws SpecialityDAOException;
}
