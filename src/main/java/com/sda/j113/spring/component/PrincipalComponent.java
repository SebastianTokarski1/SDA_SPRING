package com.sda.j113.spring.component;

import com.sda.j113.spring.model.ApplicationUser;
import com.sda.j113.spring.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author Paweł Recław, AmeN
 * @project j113_spring
 * @created 08.10.2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PrincipalComponent {
    private final ApplicationUserRepository applicationUserRepository;

    public Optional<ApplicationUser> findUser(UsernamePasswordAuthenticationToken springToken) {
        return applicationUserRepository.findByUsername(String.valueOf(springToken.getPrincipal()));
    }

    public ApplicationUser getUser(UsernamePasswordAuthenticationToken springToken) {
        return applicationUserRepository
                .findByUsername(String.valueOf(springToken.getPrincipal()))
                .orElseThrow(EntityNotFoundException::new);
    }

    public ApplicationUser getUser(UsernamePasswordAuthenticationToken springToken, Long userId) {
        ApplicationUser user = getUser(springToken);

        if (!user.getId().equals(userId)) {
            throw new IllegalStateException(String.format("Invalid user id sent in the request! %d != %d", user.getId(), userId));
        }
        return user;
    }
}
