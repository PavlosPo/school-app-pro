package gr.aueb.cf.schoolappPro.dto;

public class SpecialityUpdateDTO extends BaseDTO{
    private String speciality;

    public SpecialityUpdateDTO() {}

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
