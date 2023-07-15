package gr.aueb.cf.schoolappPro.dto;

public class TeacherUpdateDTO extends BaseDTO{  // extends because we need the id
    private String firstname;
    private String lastname;

    public TeacherUpdateDTO() {}

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
