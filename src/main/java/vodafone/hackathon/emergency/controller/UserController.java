package vodafone.hackathon.emergency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vodafone.hackathon.emergency.core.model.ApiResponse;
import vodafone.hackathon.emergency.model.request.UpdateUserRequestModel;
import vodafone.hackathon.emergency.model.response.UserResponseModel;
import vodafone.hackathon.emergency.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<UserResponseModel>> findUserById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.findUserById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseModel>>> findUsers() {
        return ResponseEntity.ok(ApiResponse.of(
                userService.findUsers()
        ));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Boolean>> updateUser(@RequestBody UpdateUserRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.updateUser(requestModel)
        ));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(
                userService.deleteUser(id)
        ));
    }
}
