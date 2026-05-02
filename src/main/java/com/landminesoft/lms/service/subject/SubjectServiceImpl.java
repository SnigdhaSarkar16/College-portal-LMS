package com.landminesoft.lms.service.subject;

import com.landminesoft.lms.entity.Subject;
import com.landminesoft.lms.exception.UserAlreadyExistsException;
import com.landminesoft.lms.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {

        // 🔴 prevent duplicate subject code
        if (subjectRepository.existsBySubjectCode(subject.getSubjectCode())) {
            throw new UserAlreadyExistsException("Subject code already exists");
        }

        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}