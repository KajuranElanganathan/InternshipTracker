package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final GeminiService geminiService;
    private final InternshipService internshipService;
    private final UsersRepository usersRepository;

    public AIController(GeminiService geminiService, InternshipService internshipService, UsersRepository usersRepository) {
        this.geminiService = geminiService;
        this.internshipService = internshipService;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/extract")
    public InternshipResponseDTO extract(
            @RequestBody UrlRequestDTO dto,
            @AuthenticationPrincipal OAuth2User oauthUser
    ) throws Exception {

        String email = oauthUser.getAttribute("email");

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Extract the internship info from Gemini
        ExtractedInternshipDTO extracted = geminiService.extractFromUrl(dto.getUrl());

        // Save to DB for this user
        return internshipService.createInternshipFromExtracted(user, extracted);
    }

}

