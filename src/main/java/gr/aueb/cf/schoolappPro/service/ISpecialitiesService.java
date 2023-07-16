package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolappPro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Speciality;
import gr.aueb.cf.schoolappPro.service.exceptions.SpecialityNotFoundException;

import java.util.List;

public interface ISpecialitiesService {
    Speciality insertSpeciality(SpecialityInsertDTO specialityInsertDTO) throws SpecialityDAOException;
    Speciality updateSpeciality(SpecialityUpdateDTO specialityUpdateDTO) throws SpecialityDAOException, SpecialityNotFoundException;
    void deleteSpeciality(Speciality speciality) throws SpecialityNotFoundException, SpecialityDAOException;

    void deleteSpeciality(int id) throws SpecialityNotFoundException, SpecialityDAOException;

    Speciality getSpeciality(String speciality) throws SpecialityNotFoundException, SpecialityDAOException;
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;
}
