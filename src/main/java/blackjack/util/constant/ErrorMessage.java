package blackjack.util.constant;

public class ErrorMessage {

    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public static final String USER_NAME_EMPTY = "플레이어 이름은 공백이 될 수 없습니다.";
    public static final String USER_NAME_LENGTH = "플레이어 이름은 5자가 넘을 수 없습니다.";

    public static final String AMOUNT_MINUS = "베팅 금액은 음수일 수 없습니다.";
    public static final String AMOUNT_ZERO = "배팅 금액은 0일 수 없습니다.";

    private ErrorMessage() {
    }
}
