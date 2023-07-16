package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolappPro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolappPro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Speciality;
import gr.aueb.cf.schoolappPro.service.exceptions.SpecialityNotFoundException;

import java.util.List;

public class SpecialityServiceImpl implements ISpecialitiesService{
    private final ISpecialityDAO dao;

    public SpecialityServiceImpl(ISpecialityDAO dao) {
        this.dao = dao;
    }

    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO specialityInsertDTO) throws SpecialityDAOException {
        Speciality speciality = mapSpecialityFromSpecialityInsertDTO(specialityInsertDTO);
        return dao.insert(speciality);
    }

    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO specialityUpdateDTO) throws SpecialityDAOException, SpecialityNotFoundException {
        Speciality speciality = mapSpecialityFromSpecialityUpdateDTO(specialityUpdateDTO);
        return dao.update(speciality);
    }

    @Override
    public void deleteSpeciality(Speciality speciality) throws SpecialityNotFoundException, SpecialityDAOException {
        if (!dao.specialityIdExists(speciality.getId()))  {
            throw new SpecialityNotFoundException("Speciality with id: " + speciality.getId() + " not found");
        }
        dao.delete(speciality.getId());
    }

    @Override
    public void deleteSpeciality(int id) throws SpecialityNotFoundException, SpecialityDAOException {
        if (!dao.specialityIdExists(id))  {
            throw new SpecialityNotFoundException("Speciality with id: " + id+ " not found");
        }
        dao.delete(id);
    }

    @Override
    public Speciality getSpeciality(String speciality) throws SpecialityNotFoundException, SpecialityDAOException {
        return dao.getBySpeciality(speciality.trim());
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        return dao.getAllSpecialities();
    }

    private Speciality mapSpecialityFromSpecialityInsertDTO(SpecialityInsertDTO specialityInsertDTO){
        return new Speciality(null, specialityInsertDTO.getSpeciality());
    }

    private Speciality mapSpecialityFromSpecialityUpdateDTO(SpecialityUpdateDTO specialityUpdateDTO) {
        return new Speciality(specialityUpdateDTO.getId(), specialityUpdateDTO.getSpeciality());
    }
}
