package com.example;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping
    public InternshipResponseDTO createInternship(@RequestBody CreateOrUpdateInternshipDTO dto,
                                                  @AuthenticationPrincipal OAuth2User oauthUser
    ) {

        String email = oauthUser.getAttribute("email");

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return internshipService.createInternship(user, dto);
    }



    @GetMapping("")
    public List<InternshipResponseDTO> getAllInternships(Authentication authentication) {

        String email = authentication.getName();

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return internshipService.getInternships(user);




    }

    @PutMapping("/{id}")
    public InternshipResponseDTO updateInternship(
            @PathVariable UUID id,
            @RequestBody CreateOrUpdateInternshipDTO dto,
            Authentication authentication) {

        String email = authentication.getName();

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return internshipService.updateInternship(user, id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        String email = authentication.getName();

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        internshipService.deleteInternship(id, user);
    }




}