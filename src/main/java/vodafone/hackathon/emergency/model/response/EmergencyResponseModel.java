package vodafone.hackathon.emergency.model.response;

import lombok.Data;

@Data
public class EmergencyResponseModel {
    private Long id;
    private String name;
    private String info;
    private String emergencyMail;
}
