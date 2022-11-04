package music_individual.demo.persistence.impl;

import music_individual.demo.persistence.UsersRepo;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepoMOCK implements UsersRepo {

    private List<UserEntity> users;

    public UsersRepoMOCK(){
        users = new ArrayList<>();
        users.add(new UserEntity(1, "martin", "1234"));
        users.add(new UserEntity(2, "testUser1", "1234"));
        users.add(new UserEntity(3, "testUser2", "1234"));
    }

    @Override
    public List<UserEntity> GetAllUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public Optional<UserEntity> GetUserByID(int id) {
        return this.users.stream().filter(UserEntity->UserEntity.getId() == id).findFirst();
    }
}
