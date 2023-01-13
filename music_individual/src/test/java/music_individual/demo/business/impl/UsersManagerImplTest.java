package music_individual.demo.business.impl;

import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void testGetAllUsersSuccess() {
        // Arrange
        List<UserEntity> expectedUsers = Arrays.asList(new UserEntity(), new UserEntity());
        when(repoMOCK.findAll()).thenReturn(expectedUsers);

        // Act
        List<UserEntity> returnedUsers = usersManagerMOCK.GetAllUsers();

        // Assert
        assertEquals(expectedUsers, returnedUsers);
    }

    @Test
    public void testGetAllUsersFail() {
        // Arrange
        List<UserEntity> expectedUsers = new ArrayList<>();
        when(repoMOCK.findAll()).thenReturn(expectedUsers);

        // Act
        List<UserEntity> returnedUsers = usersManagerMOCK.GetAllUsers();

        // Assert
        assertEquals(expectedUsers, returnedUsers);
    }



    @Test
    void testAddUserSuccess() {
        // Arrange
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        when(passwordEncoderMOCK.encode(any(String.class))).thenReturn("djngonjdopsnjo");
        when(repoMOCK.save(any(UserEntity.class))).thenReturn(user);

        // Act
        UserEntity result = usersManagerMOCK.AddUser(user);

        // Assert
        assertEquals(result.getPassword(),"djngonjdopsnjo");
    }





    @Test
    void testUpdateUserSuccess() {

        // Arrange
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        UserEntity updatedUser = UserEntity.builder().id(1).username("updatedTestUser").password("1234").email("test@gmail.com").role("tester").build();

        // Act
        usersManagerMOCK.AddUser(user);
        when(repoMOCK.findById(any(Integer.class))).thenReturn(Optional.ofNullable(updatedUser));
        usersManagerMOCK.UpdateUser(updatedUser);

        // Assert
        assertEquals(updatedUser.getUsername(), "updatedTestUser");
    }

    @Test
    public void testDeleteUserSuccess() {
        // Arrange
        Integer testId = 1;
        when(repoMOCK.findById(testId)).thenReturn(Optional.of(new UserEntity()));
        doNothing().when(repoMOCK).deleteById(testId);
        when(repoMOCK.findById(testId)).thenReturn(Optional.empty());
        // Act
        boolean result = usersManagerMOCK.DeleteUser(testId);

        // Assert
        assertTrue(result);
    }


    @Test
    public void testGetUserByIDValidID() {
        // Arrange
        Integer id = 1;
        UserEntity expectedUser = UserEntity.builder().id(id).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        when(repoMOCK.findById(id)).thenReturn(Optional.of(expectedUser));

        // Act
        UserEntity actualUser = usersManagerMOCK.GetUserByID(id);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(repoMOCK, times(2)).findById(id);
    }

    @Test
    public void testGetUserByIDInvalidID() {
        // Arrange
        Integer id = -1;
        when(repoMOCK.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ObjectMissingException.class, () -> usersManagerMOCK.GetUserByID(id));
        verify(repoMOCK, times(1)).findById(id);
    }


}