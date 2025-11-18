package com.example;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;

    public CustomOAuth2UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();

        OAuth2User oauthUser = delegate.loadUser(userRequest);

        String accessToken = userRequest.getAccessToken().getTokenValue();
        System.out.println("GitHub Access Token: " + accessToken);

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String avatar = oauthUser.getAttribute("avatar_url");

        // find or create
        Users user = usersRepository.findByEmail(email)
                .orElseGet(() -> new Users());

        // update info
        user.setEmail(email);
        user.setFirstName(name);


        // persist to DB
        usersRepository.save(user);

        // pass original OAuth2User back to security system
        return oauthUser;
    }
}
