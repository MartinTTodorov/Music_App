package music_individual.demo.business;


import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.entities.UserEntity;

import java.util.List;

public interface IUsersManager {
    public List<UserEntity> GetAllUsers();
    public UserEntity AddUser(UserEntity user);
    public void UpdateUser(UserEntity user);
    public boolean DeleteUser(Integer id);
    public UserEntity GetUserByID(Integer id);
}
