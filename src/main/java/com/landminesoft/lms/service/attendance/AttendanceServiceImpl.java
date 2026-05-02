package com.landminesoft.lms.service.attendance;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.exception.UserAlreadyExistsException;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository,
                                 FacultyRepository facultyRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Attendance markAttendance(Long facultyId, Long studentId, Long courseId, String status, LocalDate date) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UnauthorizedException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new UnauthorizedException("Course not found"));

        FacultyPersonal faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new UnauthorizedException("Faculty not found"));

        // prevent duplicate attendance
        if (attendanceRepository.existsByStudentIdAndCourseIdAndClassDate(studentId, courseId, date)) {
            throw new UserAlreadyExistsException("Attendance already marked for this date");
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setMarkedBy(faculty);
        attendance.setStatus(status);
        attendance.setClassDate(date);

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getStudentAttendance(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
}