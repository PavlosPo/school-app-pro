package gr.aueb.cf.schoolappPro.dao;

import gr.aueb.cf.schoolappPro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolappPro.model.Meeting;
import gr.aueb.cf.schoolappPro.service.util.DBUtil;
import gr.aueb.cf.schoolappPro.service.util.DateUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingDAOImpl implements IMeetingDAO{


    @Override
    public Meeting insert(Meeting meeting) throws MeetingDAOException {
        String sql = "INSERT INTO MEETINGS (TEACHER_ID, STUDENT_ID, MEETING_ROOM, MEETING_DATE) VALUES  (?, ?, ?, ?)";

        try (Connection connection  = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            Integer teacherId = meeting.getTeacherId();
            Integer studentId = meeting.getStudentId();
            String meetingRoom = meeting.getMeetingRoom();
            Date meetingDateUncasted = meeting.getMeetingDate();
            java.sql.Date meetingDateSql = DateUtil.toSQLDate(meetingDateUncasted); // cast date to sql format


            ps.setInt(1, teacherId);
            ps.setInt(2, studentId);
            ps.setString(3, meetingRoom);
            ps.setDate(4, meetingDateSql);

            int n = ps.executeUpdate(); // Execute sql script

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Insert", JOptionPane.PLAIN_MESSAGE);
                return meeting;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meeting Insert : " + meeting);
        }
    }

    @Override
    public Meeting update(Meeting meeting) throws MeetingDAOException {
        String sql = "UPDATE MEETINGS SET MEETING_ROOM = ?, MEETING_DATE = ?, WHERE TEACHER_ID = ? AND STUDENT_ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){


            Integer teacherId = meeting.getTeacherId();
            Integer studentId = meeting.getStudentId();
            String meetingRoom = meeting.getMeetingRoom();
            Date meetingDateUncasted = meeting.getMeetingDate();
            java.sql.Date meetingDateSql = DateUtil.toSQLDate(meetingDateUncasted); // cast date to sql format


            ps.setString(1, meetingRoom);
            ps.setDate(2, meetingDateSql);
            ps.setInt(3, teacherId);
            ps.setInt(4, studentId);

            int n = ps.executeUpdate();

            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Update", JOptionPane.PLAIN_MESSAGE);
                return meeting;
            } else {
                return null;
            }

        } catch (SQLException e ) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Meetings Update : " + meeting);
        }
    }

    @Override
    public void delete(int studentId, int teacherId) throws MeetingDAOException {
        String sql = "DELETE FROM MEETINGS WHERE TEACHER_ID = ? AND STUDENT_ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            ps.setInt(2, studentId);

            int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος/η?", "Warning", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int n = ps.executeUpdate();
                if (n >= 1) {
                    JOptionPane.showMessageDialog(null, n +" rows affected", "Delete", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No rows affected", "Delete", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL Error in Teacher Delete with id: " + teacherId);
        }
    }

    @Override
    public List<Meeting> getByDate(Date date) throws MeetingDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE MEETING_DATE = ?";
        List<Meeting> meetings = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            java.sql.Date meetingDateSQL = DateUtil.toSQLDate(date);

            ps.setDate(1, meetingDateSQL);

            rs = ps.executeQuery();

            while (rs.next()) {
                Meeting meeting = new Meeting(rs.getInt("TEACHER_ID"), rs.getInt("STUDENT_ID"), rs.getString("MEETING_ROOM"), rs.getDate("MEETING_DATE"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL ERROR in Meeting Select with Date : " + date);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getByMeetingRoom(String meetingRoom) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE MEETING_ROOM = ?";
        List<Meeting> meetings = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, meetingRoom);
            rs = ps.executeQuery();

            while (rs.next()) {
                Meeting meeting = new Meeting(rs.getInt("TEACHER_ID"), rs.getInt("STUDENT_ID"), rs.getString("MEETING_ROOM"), rs.getDate("MEETING_DATE"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL ERROR in Meetings Select with Meeting Room : " + meetingRoom);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getByTeacherId(int teacherId) throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS WHERE TEACHER_ID = ?";
        List<Meeting> meetings = null ;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, teacherId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Meeting newMeeting = new Meeting(rs.getInt("TEACHER_ID"), rs.getInt("STUDENT_ID"), rs.getString("MEETING_ROOM"), rs.getDate("MEETING_DATE"));
                meetings.add(newMeeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL ERROR in Meeting Select with Teacher_Id : " + teacherId);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getByStudentId(int studentId) throws MeetingDAOException {

        String sql = "SELECT * FROM MEETINGS WHERE STUDENT_ID = ?";
        List<Meeting> meetings = null ;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, studentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Meeting newMeeting = new Meeting(rs.getInt("TEACHER_ID"), rs.getInt("STUDENT_ID"), rs.getString("MEETING_ROOM"), rs.getDate("MEETING_DATE"));
                meetings.add(newMeeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("SQL ERROR in Meeting Select with Teacher_Id : " + studentId);
        } finally {
            try {
                if (rs != null) rs.close();     // close result set
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getAllMeetings() throws MeetingDAOException {
        String sql = "SELECT * FROM MEETINGS";
        List<Meeting> meetings = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            rs = ps.executeQuery();
            while (rs.next()) {
                Meeting meeting = new Meeting(rs.getInt("TEACHER_ID"), rs.getInt("STUDENT_ID"), rs.getString("MEETING_ROOM"), rs.getDate("MEETING_DATE"));
                meetings.add(meeting);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MeetingDAOException("ERROR in SQL Select all Meetings");
        }
        return meetings;
    }

    @Override
    public boolean meetingRoomExists(String meetingRoom) throws MeetingDAOException {
        for (Meeting meeting : getAllMeetings()) {
            if (meeting.getMeetingRoom().trim().equalsIgnoreCase(meetingRoom.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean meetingExists(int teacherId, int studentId) throws MeetingDAOException {
        for (Meeting meeting : getAllMeetings()) {
            if ((meeting.getTeacherId() == teacherId) && (meeting.getStudentId() == studentId)) {
                return true;
            }
        }
        return false;
    }
}
