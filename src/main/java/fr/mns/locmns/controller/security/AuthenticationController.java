package fr.mns.locmns.controller.security;

import fr.mns.locmns.dto.security.LoginRequest;
import fr.mns.locmns.service.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "authentication")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "login", produces = MediaType.TEXT_PLAIN_VALUE)
    public String login(@RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }
}
