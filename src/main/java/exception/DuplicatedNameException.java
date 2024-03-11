package exception;

public class DuplicatedNameException extends RuntimeException {
    public static final String DUPLICATED_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";

    public DuplicatedNameException(final String message) {
        super(message);
    }
}
