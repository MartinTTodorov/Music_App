package music_individual.demo.controller;


import lombok.AllArgsConstructor;
import music_individual.demo.business.IUsersManager;
import music_individual.demo.domain.CreateUserRequest;
import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import music_individual.demo.security.Authorization.isAuthorized;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class UsersController {
    final IUsersManager usersManager;



    @GetMapping()
    public ResponseEntity GetAllUsers(){
        return ResponseEntity.ok(usersManager.GetAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable(value="id") Integer id){
        return ResponseEntity.ok(usersManager.GetUserByID(id));
    }

    @PostMapping
    public ResponseEntity CreateUser(@RequestBody CreateUserRequest request){
        usersManager.AddUser(UserConverter(request));

        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity DeleteUser(@PathVariable Integer userId){

        usersManager.DeleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> UpdateUser(@PathVariable("id") Integer id, @RequestBody @Valid UpdateUserRequest request){
        request.setId(id);

        usersManager.UpdateUser(UserConverter(request));
        return ResponseEntity.noContent().build();
    }

    public UserEntity UserConverter(CreateUserRequest request){
        return UserEntity.builder().username((request.getUsername())).password(request.getPassword()).role("Listener").build();
    }

    public UserEntity UserConverter(UpdateUserRequest request){
        return UserEntity.builder().id(request.getId()).password(request.getPassword()).email(request.getEmail()).build();
    }
}
