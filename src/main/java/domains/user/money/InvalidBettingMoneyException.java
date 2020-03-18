package domains.user.money;

public class InvalidBettingMoneyException extends IllegalArgumentException{
    public static final String ZERO_OR_NEGATIVE = "숫자는 1 이상인 정수여야 합니다.";

    public InvalidBettingMoneyException(String s) {
        super(s);
    }
}
