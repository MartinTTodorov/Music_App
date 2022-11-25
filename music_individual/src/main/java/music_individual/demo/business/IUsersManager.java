package music_individual.demo.business;


import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.entities.UserEntity;

import java.util.List;

public interface IUsersManager {
    public List<UserEntity> GetAllUsers();
    public void AddUser(UserEntity user);
    public void UpdateUser(UpdateUserRequest request);
    public void DeleteUser(Integer id);
    public UserEntity GetUserByID(Integer id);
}
