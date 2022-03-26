package vodafone.hackathon.emergency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vodafone.hackathon.emergency.core.model.ApiResponse;
import vodafone.hackathon.emergency.model.request.CreateUserRequestModel;
import vodafone.hackathon.emergency.model.request.LoginRequestModel;
import vodafone.hackathon.emergency.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                authenticationService.login(requestModel)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Boolean>> register(@RequestBody CreateUserRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                authenticationService.register(requestModel)
        ));
    }
}
