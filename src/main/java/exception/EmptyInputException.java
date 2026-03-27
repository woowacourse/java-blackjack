package exception;

public class EmptyInputException extends BlackjackException{
    public EmptyInputException() {
        super("빈 값을 입력할 수 없습니다.");
    }
}
