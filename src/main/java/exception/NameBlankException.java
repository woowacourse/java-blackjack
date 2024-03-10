package exception;

public class NameBlankException extends RuntimeException {
    public static final String NOT_ALLOWED_BLANK_NAME = "이름을 공백으로 사용할 수 없습니다.";

    public NameBlankException(final String message) {
        super(message);
    }
}
