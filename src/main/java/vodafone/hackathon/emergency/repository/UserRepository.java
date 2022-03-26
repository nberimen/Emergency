package vodafone.hackathon.emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vodafone.hackathon.emergency.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMail(String mail);

    User findByMail(String mail);

    User getUserById(long id);
}
