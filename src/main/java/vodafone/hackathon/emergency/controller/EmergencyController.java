package vodafone.hackathon.emergency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vodafone.hackathon.emergency.core.model.ApiResponse;
import vodafone.hackathon.emergency.model.request.CreateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.response.EmergencyResponseModel;
import vodafone.hackathon.emergency.service.EmergencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emergencies")
public class EmergencyController {
    private final EmergencyService emergencyService;

    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> createEmergency(@RequestBody CreateEmergencyRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                emergencyService.createEmergency(requestModel)
        ));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<EmergencyResponseModel>> findEmergencyById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.of(
                emergencyService.findEmergencyById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmergencyResponseModel>>> findEmergencies() {
        return ResponseEntity.ok(ApiResponse.of(
                emergencyService.findEmergencies()
        ));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Boolean>> updateEmergency(@RequestBody UpdateEmergencyRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                emergencyService.updateEmergency(requestModel)
        ));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteEmergency(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(
                emergencyService.deleteEmergency(id)
        ));
    }
}
