package com.example.stazaleksandergut.controllers;

import com.example.stazaleksandergut.dto.UserDto;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    /**
     * Get rest controller for get an information about session
     *
     * @return information about session wrapped to user model
     */
    @RolesAllowed("USER")
    @GetMapping()
    public ResponseEntity<UserDto> getUser() {
        var user = getUserInfo();
        return ResponseEntity.ok(user);
    }

    /**
     * This method returns User Model with data from accessToken
     *
     * @return User model with session data
     */
    private UserDto getUserInfo() {

        if (isAnonymousUser()) return null;

        var accessToken = getAccessTokenInfo();
        return UserDto.builder().firstName(accessToken.getGivenName()).lastName(accessToken.getFamilyName())
                .id(accessToken.getSubject()).email(accessToken.getEmail())
                .build();
    }

    /**
     * Checking that user is anonymous
     *
     * @return true when guest is an anonymous or false
     */
    private boolean isAnonymousUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }

    /**
     * Getter for AccessToken of Keycloak
     *
     * @return AccessToken from Keycloak auth
     */
    private AccessToken getAccessTokenInfo() {
        var auth = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var account = (SimpleKeycloakAccount) auth.getDetails();
        return account.getKeycloakSecurityContext().getToken();
    }
}
