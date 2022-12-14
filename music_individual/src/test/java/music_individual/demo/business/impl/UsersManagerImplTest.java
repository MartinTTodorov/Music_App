package music_individual.demo.business.impl;

import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersManagerImplTest{

    @Mock
    private UsersRepusitory repoMOCK;

    @Mock
    private PasswordEncoder passwordEncoderMOCK;

    @InjectMocks
    private UsersManagerImpl usersManagerMOCK;

    @Test
    void getAllUsers() {
        //Happy flow
        //Check if all users are returned
        List<UserEntity> users = new ArrayList<UserEntity>();
        when(repoMOCK.findAll()).thenReturn(users);
        List<UserEntity> newUsers = repoMOCK.findAll();
        assertEquals(users, newUsers);
        verify(repoMOCK).findAll();
    }

    @Test
    void addUser() {
        //Happy flow
        //Check if a user is added
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        when(passwordEncoderMOCK.encode(any(String.class))).thenReturn("djngonjdopsnjo");
        when(repoMOCK.save(any(UserEntity.class))).thenReturn(user);
        UserEntity result = usersManagerMOCK.AddUser(user);
        assertEquals(result.getPassword(),"djngonjdopsnjo");
    }

    @Test
    void updateUser() {
        //happy flow
        //Test if username updates properly
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        UserEntity updatedUser = UserEntity.builder().id(1).username("updatedTestUser").password("1234").email("test@gmail.com").role("tester").build();
        usersManagerMOCK.AddUser(user);
        when(repoMOCK.findById(any(Integer.class))).thenReturn(Optional.ofNullable(updatedUser));
        usersManagerMOCK.UpdateUser(updatedUser);
        assertEquals(updatedUser.getUsername(), "updatedTestUser");
    }

    @Test
    boolean deleteUser() {
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();

        when(repoMOCK.findById(1)).thenReturn(Optional.of(new UserEntity()));
        assertEquals(usersManagerMOCK.DeleteUser(1), true);

        return false;

    }

    @Test
    void getUserByID() {
//        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
//
//        when(repoMOCK.findById(1)).thenReturn(Optional.of(user));
//
//        UserEntity result = usersManagerMOCK.GetUserByID(1);
//
//        assertEquals(user, result);
//        verify(repoMOCK).findById(1);
    }
}