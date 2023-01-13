package music_individual.demo.controller;

import music_individual.demo.business.ILoginManager;
import music_individual.demo.business.exception.InvalidCredentialsException;
import music_individual.demo.domain.LoginRequest;
import music_individual.demo.domain.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Mock
    private ILoginManager loginManager;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void testLoginSuccess() {
        //Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("username");
        request.setPassword("password");
        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setAccessToken("abc123");
        when(loginManager.login(request)).thenReturn(expectedResponse);
        //Act
        ResponseEntity<LoginResponse> response = loginController.login(request);
        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(loginManager).login(request);
    }

    @Test
    public void testLoginFail() {
        //Arrange
        LoginRequest request = new LoginRequest();
        request.setUsername("username");
        request.setPassword("password");
        when(loginManager.login(request)).thenThrow(new InvalidCredentialsException());
        //Act
        try {
            loginController.login(request);
            fail("Expected InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            //Assert
            assertEquals("Invalid username or password", e.getMessage());
            verify(loginManager).login(request);
        }
    }

}