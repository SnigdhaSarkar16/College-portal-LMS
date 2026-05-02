package com.landminesoft.lms.service.marks;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public MarksServiceImpl(MarksRepository marksRepository,
                            StudentRepository studentRepository,
                            CourseRepository courseRepository) {
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Marks addOrUpdateMarks(Long studentId, Long courseId, Integer theory, Integer practical) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UnauthorizedException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new UnauthorizedException("Course not found"));

        Marks marks = marksRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElse(new Marks());

        marks.setStudent(student);
        marks.setCourse(course);
        marks.setSubject(course.getSubject());

        marks.setTheoryMarks(theory);
        marks.setPracticalMarks(practical);

        int total = theory + practical;
        marks.setTotalMarks(total);

        marks.setGrade(calculateGrade(total));

        marks.setSemester(course.getSemester());
        marks.setAcademicYear(course.getAcademicYear());

        return marksRepository.save(marks);
    }

    @Override
    public List<Marks> getStudentMarks(Long studentId) {
        return marksRepository.findByStudentId(studentId);
    }

    // 🎯 Grade logic
    private String calculateGrade(int total) {
        if (total >= 90) return "A+";
        if (total >= 80) return "A";
        if (total >= 70) return "B+";
        if (total >= 60) return "B";
        if (total >= 50) return "C";
        if (total >= 40) return "D";
        return "F";
    }
}