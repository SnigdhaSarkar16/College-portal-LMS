package com.landminesoft.lms.service.enrollment;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.exception.UserAlreadyExistsException;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UnauthorizedException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new UnauthorizedException("Course not found"));

        // prevent duplicate enrollment
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new UserAlreadyExistsException("Already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setSubject(course.getSubject());
        enrollment.setSemester(course.getSemester());
        enrollment.setAcademicYear(course.getAcademicYear());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}