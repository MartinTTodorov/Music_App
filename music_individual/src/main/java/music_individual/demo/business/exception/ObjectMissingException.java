package music_individual.demo.business.exception;

public class ObjectMissingException extends RuntimeException{
    public ObjectMissingException(){
        super("Object was not found");
    }
}
