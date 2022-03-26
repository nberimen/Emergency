package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.core.security.JwtTokenGenerator;
import vodafone.hackathon.emergency.core.security.JwtUserDetails;
import vodafone.hackathon.emergency.model.User;
import vodafone.hackathon.emergency.model.request.CreateUserRequestModel;
import vodafone.hackathon.emergency.model.request.LoginRequestModel;
import vodafone.hackathon.emergency.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public boolean register(CreateUserRequestModel requestModel) {
        return userService.createUser(requestModel);
    }

    public String login(LoginRequestModel requestModel) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        return "Bearer " + token;
    }

    public User getCurrentUser() {
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        User user = null;
        if (jwtUserDetails != null) {
            user = userRepository.getUserById(jwtUserDetails.getId());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is null.");
            }
        }

        return user;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }

        return jwtUserDetails;
    }
}
