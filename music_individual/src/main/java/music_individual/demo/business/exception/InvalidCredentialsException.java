package music_individual.demo.business.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid username or password");
    }
}
