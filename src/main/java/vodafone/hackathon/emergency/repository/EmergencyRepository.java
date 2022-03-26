package vodafone.hackathon.emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vodafone.hackathon.emergency.model.Emergency;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    boolean existsByName(String name);
}
