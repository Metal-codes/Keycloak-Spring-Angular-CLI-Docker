package com.example.stazaleksandergut.controllers;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    /**
     * Its only for tests
     * @return ResponseEntity<String> Hello world! or if logged in - hello #email
     */
    @GetMapping("/login")
    public ResponseEntity<String> getAboutUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.ok("Hello World!");
        }
        return ResponseEntity.ok("Hello " + getAccessTokenInfo().getEmail() + "!");
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
