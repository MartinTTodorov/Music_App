package music_individual.demo.business.impl;

import lombok.AllArgsConstructor;
import music_individual.demo.business.IUsersManager;
import music_individual.demo.business.exception.FailedCRUDException;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersManagerImpl implements IUsersManager {
    @Autowired
    private UsersRepusitory repo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> GetAllUsers() {
        return repo.findAll().stream().toList();
    }

    @Override
    public UserEntity AddUser(UserEntity user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return repo.save(user);
    }

    @Override
    public void UpdateUser(UserEntity user){
        Optional<UserEntity> userOptional = repo.findById(user.getId());
        if(userOptional.isPresent()) {
            UserEntity updatedUser = userOptional.get();

            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            repo.save(updatedUser);
        }
        else{
            throw new ObjectMissingException("User not found");
        }

    }

    @Override
    public boolean DeleteUser(Integer id){
        repo.deleteById(id);
        if(repo.findById(id).isPresent()){
            throw new FailedCRUDException("User was not deleted successfully");
        }
        return true;
    }

    @Override
    public UserEntity GetUserByID(Integer id) {
        if(!repo.findById(id).isPresent()){
            throw new ObjectMissingException("User not found");
        }
        return repo.findById(id).get();
    }
}
