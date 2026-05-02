package com.landminesoft.lms.service.course;

import com.landminesoft.lms.entity.Course;
import com.landminesoft.lms.entity.FacultyPersonal;
import com.landminesoft.lms.entity.Subject;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.CourseRepository;
import com.landminesoft.lms.repository.FacultyRepository;
import com.landminesoft.lms.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             FacultyRepository facultyRepository,
                             SubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Course createCourse(Long facultyId, Long subjectId, Course course) {

        FacultyPersonal faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new UnauthorizedException("Faculty not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new UnauthorizedException("Subject not found"));

        course.setFaculty(faculty);
        course.setSubject(subject);

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}