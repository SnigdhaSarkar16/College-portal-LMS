package com.landminesoft.lms.service.announcement;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AdminRepository adminRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository,
                                   AdminRepository adminRepository) {
        this.announcementRepository = announcementRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public Announcement createAnnouncement(Long adminId, Announcement announcement) {

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new UnauthorizedException("Admin not found"));

        announcement.setCreatedBy(admin);

        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAllAnnouncements(String role) {

        List<Announcement> all = announcementRepository.findAll();

        return all.stream()
                .filter(a ->
                        a.getTargetAudience().equals("ALL") ||
                                a.getTargetAudience().equals(role)
                )
                .filter(a ->
                        a.getExpiresAt() == null ||
                                a.getExpiresAt().isAfter(LocalDate.now())
                )
                .collect(Collectors.toList());
    }
}