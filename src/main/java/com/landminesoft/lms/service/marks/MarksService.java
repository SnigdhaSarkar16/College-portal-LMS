package com.landminesoft.lms.service.marks;

import com.landminesoft.lms.entity.Marks;

import java.util.List;

public interface MarksService {

    Marks addOrUpdateMarks(Long studentId, Long courseId, Integer theory, Integer practical);

    List<Marks> getStudentMarks(Long studentId);
}