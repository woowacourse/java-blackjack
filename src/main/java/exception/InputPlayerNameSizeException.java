package exception;

public class InputPlayerNameSizeException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 플레이어는 최대 %d명입니다.";

    public InputPlayerNameSizeException(int maxPlayerSize) {
        super(String.format(MESSAGE, maxPlayerSize));
    }
}
