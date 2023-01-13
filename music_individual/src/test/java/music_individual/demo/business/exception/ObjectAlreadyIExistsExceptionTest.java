package music_individual.demo.business.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectAlreadyIExistsExceptionTest {

    @Test
    public void testObjectAlreadyIExistsException() {
        // Arrange
        String expectedMessage = "Object already exists";
        ObjectAlreadyIExistsException exception = new ObjectAlreadyIExistsException(expectedMessage);

        // Act
        String message = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, message);
    }

    @Test
    public void testObjectAlreadyIExistsExceptionTryCatch() {
        // Arrange
        String expectedMessage = "Object already exists";
        // Act
        try {
            throw new ObjectAlreadyIExistsException(expectedMessage);
        } catch (ObjectAlreadyIExistsException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }





}