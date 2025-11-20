package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;
    private final UsersRepository usersRepository;

    public InternshipController(InternshipService internshipService, UsersRepository usersRepository) {
        this.internshipService = internshipService;
        this.usersRepository = usersRepository;
    }

    // Helper method to get the logged-in user
    private Users getLoggedInUser(OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public InternshipResponseDTO createInternship(
            @RequestBody CreateOrUpdateInternshipDTO dto,
            @AuthenticationPrincipal OAuth2User oauthUser
    ) {
        Users user = getLoggedInUser(oauthUser);
        return internshipService.createInternship(user, dto);
    }

    @GetMapping("")
    public List<InternshipResponseDTO> getAllInternships(
            @AuthenticationPrincipal OAuth2User oauthUser
    ) {
        Users user = getLoggedInUser(oauthUser);
        return internshipService.getInternships(user);
    }

    @PutMapping("/{id}")
    public InternshipResponseDTO updateInternship(
            @PathVariable UUID id,
            @RequestBody CreateOrUpdateInternshipDTO dto,
            @AuthenticationPrincipal OAuth2User oauthUser
    ) {
        Users user = getLoggedInUser(oauthUser);
        return internshipService.updateInternship(user, id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(
            @PathVariable UUID id,
            @AuthenticationPrincipal OAuth2User oauthUser
    ) {
        Users user = getLoggedInUser(oauthUser);
        internshipService.deleteInternship(id, user);
    }

}
