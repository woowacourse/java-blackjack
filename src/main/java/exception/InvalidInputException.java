package exception;

public class InvalidInputException extends BlackjackException {
    public InvalidInputException() {
        super("입력이 올바르지 않습니다.");
    }
}
