package exception;

public class BlankNameException extends BlackjackException{
    public BlankNameException() {
        super("빈 값을 입력할 수 없습니다.");
    }
}
