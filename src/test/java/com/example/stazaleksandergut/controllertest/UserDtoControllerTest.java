package com.example.stazaleksandergut.controllertest;


import com.c4_soft.springaddons.security.oauth2.test.annotations.IdTokenClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OidcStandardClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.*;
import com.c4_soft.springaddons.security.oauth2.test.mockmvc.keycloak.ServletKeycloakAuthUnitTestingSupport;
import com.example.stazaleksandergut.config.SecurityConfig;
import com.example.stazaleksandergut.controllers.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecurityConfig.class})
@WebMvcTest(controllers = UserController.class)
@Import(
        {
                UserController.class,
                ServletKeycloakAuthUnitTestingSupport.UnitTestConfig.class
        }
)
@Slf4j
public class UserDtoControllerTest {
    @MockBean
    JwtDecoder jwtDecoder;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockKeycloakAuth(
            authorities = "ROLE_USER",
            id = @IdTokenClaims(sub = "32"),
            oidc = @OidcStandardClaims(
                    nickName = "Test",
                    emailVerified = true,
                    preferredUsername = "test",
                    email = "test@gut.codes",
                    familyName = "Gut",
                    givenName =  "Tester"
            )
    )
    public void getUserDetailsAsAuthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    log.info("{}", mvcResult.getResponse().getContentAsString());
                });

    }

    @Test
    @WithMockKeycloakAuth(
            id = @IdTokenClaims(sub = "32"),
            oidc = @OidcStandardClaims(
                    nickName = "Test",
                    emailVerified = true,

                    preferredUsername = "test",
                    email = "test@gut.codes",
                    familyName = "Gut",
                    givenName =  "Tester"
            )
    )
    public void getUserDetailsAsUnauthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isForbidden())
                .andDo(mvcResult -> {
                    log.info("{}", mvcResult.getResponse().getContentAsString());
                });

    }

    @Test
    @WithMockKeycloakAuth()
    public void getUserDetailsAsAnonymous() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isForbidden())
                .andDo(mvcResult -> {
                    log.info("{}", mvcResult.getResponse().getContentAsString());
                });

    }
}
