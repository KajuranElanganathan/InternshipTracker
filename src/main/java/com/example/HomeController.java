package com.example;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private final UsersRepository usersRepository;

    public HomeController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/home")
    public String home() {return "home!";}

    @GetMapping("/secured")
    public String secured() {return "secured!";}

    @GetMapping("/api/user")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OAuth2User oauthUser) {
        Map<String, Object> userInfo = new HashMap<>();
        if (oauthUser != null) {
            String email = oauthUser.getAttribute("email");
            userInfo.put("email", email);
            userInfo.put("name", oauthUser.getAttribute("name"));
            userInfo.put("avatar", oauthUser.getAttribute("avatar_url"));
            
            usersRepository.findByEmail(email).ifPresent(user -> {
                userInfo.put("id", user.getId());
                userInfo.put("firstName", user.getFirstName());
            });
        }
        return userInfo;
    }

}