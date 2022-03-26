package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.model.Emergency;
import vodafone.hackathon.emergency.model.request.CreateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateEmergencyRequestModel;
import vodafone.hackathon.emergency.model.response.EmergencyResponseModel;
import vodafone.hackathon.emergency.repository.EmergencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmergencyService {
    private final EmergencyRepository emergencyRepository;
    private final ModelMapper mapper;

    public boolean createEmergency(CreateEmergencyRequestModel requestModel) {
        if (emergencyRepository.existsByName(requestModel.getName()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Emergency already exists.");
        Emergency emergency = mapper.map(requestModel, Emergency.class);
        emergencyRepository.save(emergency);
        return true;
    }

    public EmergencyResponseModel findEmergencyById(long id) {
        if (!emergencyRepository.existsById(id))
            return null;
        return mapper.map(emergencyRepository.getById(id), EmergencyResponseModel.class);
    }

    public List<EmergencyResponseModel> findEmergencies() {
        List<Emergency> emergencies = emergencyRepository.findAll();
        return emergencies.stream().map(x -> mapper.map(x, EmergencyResponseModel.class)).collect(Collectors.toList());
    }

    public boolean deleteEmergency(long id) {
        if (!emergencyRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found  by that id.");
        emergencyRepository.delete(emergencyRepository.getById(id));
        return true;
    }

    public boolean updateEmergency(UpdateEmergencyRequestModel requestModel) {
        if (!emergencyRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found by that id");
        Emergency emergency = emergencyRepository.getById(requestModel.getId());
        mapper.map(requestModel, emergency);
        emergencyRepository.save(emergency);
        return true;
    }
}
