package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class UpdateUserRequestModel {
    private Long id;
    private String mail;
    private String password;
}
