package exception;

public class BlankInputException extends BlackjackException {
    public BlankInputException() {
        super("빈 값은 입력할 수 없습니다.");
    }
}
