package music_individual.demo.business.impl;

import lombok.AllArgsConstructor;
import music_individual.demo.business.IUsersManager;
import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
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
    public void AddUser(UserEntity user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
    }

    @Override
    public void UpdateUser(UpdateUserRequest request){
        Optional<UserEntity> userOptional = repo.findById(request.getId());
        if(userOptional.isPresent()) {
            UserEntity user = userOptional.get();


            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            repo.save(user);
        }

    }

    @Override
    public void DeleteUser(Integer id){

        repo.deleteById(id);
    }

    @Override
    public UserEntity GetUserByID(Integer id) {


        return repo.findById(id).get();
    }
}
