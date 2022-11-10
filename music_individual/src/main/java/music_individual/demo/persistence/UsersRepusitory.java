package music_individual.demo.persistence;


import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepusitory extends JpaRepository<UserEntity, Integer> {

}
