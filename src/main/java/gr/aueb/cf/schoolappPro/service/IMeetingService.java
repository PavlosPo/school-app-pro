package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolappPro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappPro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolappPro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Meeting;
import gr.aueb.cf.schoolappPro.service.exceptions.MeetingNotFoundException;

import java.util.Date;
import java.util.List;

public interface IMeetingService {
    Meeting insertMeeting(MeetingInsertDTO meetingInsertDTO) throws MeetingDAOException;
    Meeting updateMeeting(MeetingUpdateDTO meetingUpdateDTO) throws MeetingDAOException, MeetingNotFoundException;
    void deleteMeeting(int teacherId, int studentId) throws MeetingDAOException, MeetingNotFoundException;
    List<Meeting> getMeetingsFromMeetingDate(Date meetingDate) throws MeetingDAOException;
    List<Meeting> getMeetingsFromMeetingRoom(String meetingRoom) throws MeetingDAOException;
    List<Meeting> getAllMeetings() throws MeetingDAOException;
}
