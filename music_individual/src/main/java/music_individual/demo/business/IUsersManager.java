package music_individual.demo.business;


import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.entities.UserEntity;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUsersManager {
    public List<UserEntity> GetAllUsers();
    public void AddUser(UserEntity user) throws Exception;
    public void UpdateUser(UpdateUserRequest request) throws Exception;
    public void DeleteUser(Integer id) throws Exception;
    public UserEntity GetUserByID(Integer id);
}
