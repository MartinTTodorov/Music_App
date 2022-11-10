package music_individual.demo.business.impl;

import music_individual.demo.business.IUsersManager;
import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersManagerImpl implements IUsersManager {
    @Autowired
    private UsersRepusitory repo;

    @Override
    public List<UserEntity> GetAllUsers() {
        return repo.findAll().stream().toList();
    }

    @Override
    public void AddUser(UserEntity user) throws Exception{
        //Check if user exists and throw exception if it does
        if(repo.existsById(user.getId())){
            throw new Exception("User with this ID already exists");
        }
        repo.save(user);
    }

    @Override
    public void UpdateUser(UpdateUserRequest request) throws Exception{
        Optional<UserEntity> userOptional = repo.findById(request.getId());
        //Check if id exists in repo
        if(!repo.existsById(request.getId())){
            throw new Exception("User with this ID does not exist");
        }
        UserEntity user = userOptional.get();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        repo.save(user);


    }

    @Override
    public void DeleteUser(Integer id) throws Exception{
        //Check if ID exists in repo
        if(!repo.existsById(id)){
            throw new Exception("User with this ID does not exist");
        }
        repo.deleteById(id);
    }

    @Override
    public UserEntity GetUserByID(Integer id) {

        return repo.findById(id).get();
    }
}
