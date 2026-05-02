package com.landminesoft.lms.service.course;

import com.landminesoft.lms.entity.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Long facultyId, Long subjectId, Course course);

    List<Course> getAllCourses();
}