package domain.gamer;

public class WhiteSpaceNameException extends IllegalArgumentException {
    public WhiteSpaceNameException() {
        super("공백 문자가 입력되었습니다.");
    }
}
