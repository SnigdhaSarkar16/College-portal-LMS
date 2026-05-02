package com.landminesoft.lms.service.attendance;

import com.landminesoft.lms.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Long facultyId, Long studentId, Long courseId, String status, LocalDate date);

    List<Attendance> getStudentAttendance(Long studentId);
}
