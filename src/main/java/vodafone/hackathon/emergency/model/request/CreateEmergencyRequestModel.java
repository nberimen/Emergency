package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class CreateEmergencyRequestModel {
    private String name;
    private String info;
    private String emergencyMail;
}
