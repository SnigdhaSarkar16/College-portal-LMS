package com.landminesoft.lms.controller.announcement;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.repository.AdminRepository;
import com.landminesoft.lms.service.announcement.AnnouncementService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AdminRepository adminRepository;

    public AnnouncementController(AnnouncementService announcementService,
                                  AdminRepository adminRepository) {
        this.announcementService = announcementService;
        this.adminRepository = adminRepository;
    }

    // 🔐 ADMIN creates announcement
    @PostMapping("/admin/announcements")
    public ResponseEntity<Announcement> create(
            @RequestBody Announcement announcement,
            Authentication authentication
    ) {
        String email = authentication.getName();

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        return ResponseEntity.ok(
                announcementService.createAnnouncement(admin.getId(), announcement)
        );
    }

    // 🌍 ALL USERS view announcements
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getAll(Authentication authentication) {

        String role = "STUDENT";

        if (authentication != null && authentication.getAuthorities() != null) {
            String authRole = authentication.getAuthorities().iterator().next().getAuthority();
            role = authRole.replace("ROLE_", "");
        }

        return ResponseEntity.ok(
                announcementService.getAllAnnouncements(role)
        );
    }
}