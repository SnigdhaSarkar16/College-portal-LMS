package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByTargetAudience(String targetAudience);
}