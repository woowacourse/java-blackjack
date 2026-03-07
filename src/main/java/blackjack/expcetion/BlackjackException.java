package expcetion;

public class BlackjackException extends IllegalArgumentException {

    public BlackjackException(expcetion.ExceptionMessage message) {
        super(message.message());
    }

}