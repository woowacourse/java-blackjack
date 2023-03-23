package exception;

public class IllegalBetValueException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 플레이어의 베팅 금액은 1000원 단위로 이뤄져야 합니다.";

    public IllegalBetValueException() {
        super(MESSAGE);
    }
}
