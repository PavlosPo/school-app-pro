package gr.aueb.cf.schoolappPro.service.exceptions;

import gr.aueb.cf.schoolappPro.model.Teacher;

public class TeacherNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + " was not found");
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
