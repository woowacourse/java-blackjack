package exception;

public class DuplicatedPlayerNameException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 플레이어 이름은 중복될 수 없습니다.";

    public DuplicatedPlayerNameException() {
        super(MESSAGE);
    }
}
