package exception;

public class NoPlayerHasThatNameException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 존재하지 않는 User 이름입니다.";

    public NoPlayerHasThatNameException() {
        super(MESSAGE);
    }
}
