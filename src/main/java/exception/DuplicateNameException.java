package exception;

public class DuplicateNameException extends BlackjackException{
    public DuplicateNameException() {
        super("이름은 중복될 수 없습니다.");
    }
}
