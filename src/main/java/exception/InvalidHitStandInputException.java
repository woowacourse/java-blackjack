package exception;

public class InvalidHitStandInputException extends BlackjackException{
    public InvalidHitStandInputException() {
        super("입력은 y 또는 n으로만 입력해야 합니다.");
    }
}
