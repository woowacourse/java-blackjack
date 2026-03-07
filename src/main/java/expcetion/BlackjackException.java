package expcetion;

public class BlackjackException extends IllegalArgumentException {

    public BlackjackException(ExceptionMessage message) {
        super(message.message());
    }

}
