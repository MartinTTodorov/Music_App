package music_individual.demo.business.exception;

public class FailedCRUDException extends RuntimeException{
    public FailedCRUDException(String message){
        super(message);
    }
}
