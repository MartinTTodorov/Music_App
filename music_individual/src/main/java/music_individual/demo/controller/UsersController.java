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
        try{

        usersManager.AddUser(UserEntity.builder().username((request.getUsername())).password(request.getPassword()).build());
        }
        catch(Exception exception){
            //handle here
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity DeleteUser(@PathVariable Integer userId){
        try{
        usersManager.DeleteUser(userId);}
        catch(Exception ex){
           //handle here
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> UpdateUser(@PathVariable("id") Integer id, @RequestBody @Valid UpdateUserRequest request){
        request.setId(id);
        try{
        usersManager.UpdateUser(request);
        }
        catch(Exception ex){
            //handle here
        }
        return ResponseEntity.noContent().build();
    }
}
