package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class UpdateEmergencyRequestModel {
    private Long id;
    private String name;
    private String info;
    private String emergencyMail;
}
