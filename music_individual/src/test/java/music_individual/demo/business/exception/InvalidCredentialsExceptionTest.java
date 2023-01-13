package music_individual.demo.business.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {

    @Test
    public void testInvalidCredentialsException() {
        // Arrange
        InvalidCredentialsException exception = new InvalidCredentialsException();

        // Act
        String message = exception.getMessage();

        // Assert
        assertEquals("Invalid username or password", message);
    }

    @Test
    public void testInvalidCredentialsExceptionTryCatch() {
        // Arrange
        String expectedMessage = "Invalid username or password";
        // Act
        try {
            throw new InvalidCredentialsException();
        } catch (InvalidCredentialsException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}