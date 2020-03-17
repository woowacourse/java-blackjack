package domains.user;

public class InvalidBettingMoneyException extends IllegalArgumentException {
    public static final String NOT_NUMBER_FORMAT = "숫자를 입력해주세요.";
    public static final String NULL_OR_EMPTY = "Null 혹은 빈 문자열이 입력되었습니다.";
    public static final String ZERO_OR_NEGATIVE = "숫자는 1 이상인 정수여야 합니다.";

    public InvalidBettingMoneyException(String s) {
        super(s);
    }
}
