package gr.aueb.cf.schoolappPro.dto;

public class TeacherUpdateDTO extends BaseDTO{  // extends because we need the id
    private Integer ssn;
    private String firstname;
    private String lastname;
    private Integer specialityId;
    private Integer userId;

    public TeacherUpdateDTO() {}

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
