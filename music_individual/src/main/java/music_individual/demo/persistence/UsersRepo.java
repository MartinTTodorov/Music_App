package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UsersRepo {
    List<UserEntity> GetAllUsers();

    Optional<UserEntity> GetUserByID(int id);
}
