package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolappPro.model.Meeting;

import java.util.Date;
import java.util.List;

public interface IMeetingDAO {
    Meeting insert(Meeting meeting) throws MeetingDAOException;
    Meeting update(Meeting meeting) throws MeetingDAOException;
    void delete(int studentId, int teacherId) throws MeetingDAOException;
    List<Meeting> getByDate(Date date) throws MeetingDAOException;
    List<Meeting> getByMeetingRoom(String meetingRoom) throws MeetingDAOException;
    List<Meeting> getByTeacherId(int teacherId) throws MeetingDAOException;
    List<Meeting> getByStudentId(int studentId) throws MeetingDAOException;

    List<Meeting> getAllMeetings() throws MeetingDAOException;

    boolean meetingRoomExists(String meetingRoom) throws MeetingDAOException;
    boolean meetingExists(int teacherId, int studentId) throws MeetingDAOException;

}
