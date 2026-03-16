package exception;

public class BlackjackException extends RuntimeException{
    public BlackjackException(String message) {
        super("[ERROR] " + message);
    }
}
