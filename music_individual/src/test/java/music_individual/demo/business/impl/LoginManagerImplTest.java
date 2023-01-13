package music_individual.demo.business.impl;

import music_individual.demo.business.exception.InvalidCredentialsException;
import music_individual.demo.domain.LoginRequest;
import music_individual.demo.persistence.UsersRepusitory;
import music_individual.demo.persistence.entities.UserEntity;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginManagerImplTest {
    @Mock
    private UsersRepusitory repoMOCK;
    @Mock
    private PasswordEncoder passwordEncoderMOCK;
    @InjectMocks
    private LoginManagerImpl loginManagerMOCK;
    @Test
    public void testLoginInvalidCredentials() {
        //Arrange
        LoginRequest request = LoginRequest.builder().username("username").password("password").build();
        UserEntity user = UserEntity.builder().username("username").password("hashed_password").build();
        when(repoMOCK.findByUsername(request.getUsername())).thenReturn(user);
        when(passwordEncoderMOCK.matches(request.getPassword(), user.getPassword())).thenReturn(false);
        try {
            //Act
            loginManagerMOCK.login(request);
            fail("Expected InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            //Assert
            verify(repoMOCK, times(1)).findByUsername(request.getUsername());
        }
    }
}