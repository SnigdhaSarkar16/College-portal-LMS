package com.landminesoft.lms.service.announcement;

import com.landminesoft.lms.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement createAnnouncement(Long adminId, Announcement announcement);

    List<Announcement> getAllAnnouncements(String role);
}