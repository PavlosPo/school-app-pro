package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.IMeetingDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolappPro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolappPro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolappPro.model.Meeting;
import gr.aueb.cf.schoolappPro.service.exceptions.MeetingNotFoundException;

import java.util.Date;
import java.util.List;

public class MeetingServiceImpl implements IMeetingService{
    private final IMeetingDAO dao;

    public MeetingServiceImpl(IMeetingDAO dao) {
        this.dao = dao;
    }


    @Override
    public Meeting insertMeeting(MeetingInsertDTO meetingInsertDTO) throws MeetingDAOException {
        Meeting meeting =  mapMeetingFromInsertMeetingDTO(meetingInsertDTO);
        return dao.insert(meeting);
    }

    @Override
    public Meeting updateMeeting(MeetingUpdateDTO meetingUpdateDTO) throws MeetingDAOException, MeetingNotFoundException {
        Meeting meeting = mapMeetingFromUpdateMeetingDTO(meetingUpdateDTO);
        if (!dao.meetingExists(meeting.getTeacherId(), meeting.getStudentId())) {
            throw new MeetingNotFoundException("Meeting with teacher id: " + meetingUpdateDTO.getTeacherId()
            + " and student id: " + meetingUpdateDTO.getStudentId() + " not found");
        }
        return dao.update(meeting);
    }

    @Override
    public void deleteMeeting(int teacherId, int studentId) throws MeetingDAOException, MeetingNotFoundException {
        if (!dao.meetingExists(teacherId, studentId)) {
            throw new MeetingNotFoundException("Meeting with teacher id: " + teacherId
                    + " and student id: " + studentId + " not found");
        }
        dao.delete(teacherId, studentId);
    }

    @Override
    public List<Meeting> getMeetingsFromMeetingDate(Date meetingDate) throws MeetingDAOException {
        return dao.getByDate(meetingDate);
    }

    @Override
    public List<Meeting> getMeetingsFromMeetingRoom(String meetingRoom) throws MeetingDAOException{
     return dao.getByMeetingRoom(meetingRoom);
    }

    @Override
    public List<Meeting> getAllMeetings() throws MeetingDAOException {
        return dao.getAllMeetings();
    }

    private Meeting mapMeetingFromInsertMeetingDTO(MeetingInsertDTO meetingInsertDTO) {
        return new Meeting(meetingInsertDTO.getTeacherId(), meetingInsertDTO.getStudentId(), meetingInsertDTO.getMeetingRoom(), meetingInsertDTO.getMeetingDate());
    }

    private Meeting mapMeetingFromUpdateMeetingDTO(MeetingUpdateDTO meetingUpdateDTO) {
        return new Meeting(meetingUpdateDTO.getTeacherId(), meetingUpdateDTO.getStudentId(), meetingUpdateDTO.getMeetingRoom(), meetingUpdateDTO.getMeetingDate());
    }
}
