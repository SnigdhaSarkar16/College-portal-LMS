package com.landminesoft.lms.service.subject;

import com.landminesoft.lms.entity.Subject;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    List<Subject> getAllSubjects();
}