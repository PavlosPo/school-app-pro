package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.model.Speciality;

import java.util.List;

public interface ISpecialityDAO {
    Speciality insert(Speciality speciality) throws SpecialityDAOException;
    Speciality update(Speciality speciality) throws SpecialityDAOException;
    void delete(Speciality speciality) throws SpecialityDAOException;
    List<Speciality> getBySpeciality(String speciality) throws SpecialityDAOException;
    Speciality getById(Speciality speciality) throws SpecialityDAOException;
}