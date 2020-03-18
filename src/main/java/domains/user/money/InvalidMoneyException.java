package domains.user.money;

public class InvalidMoneyException extends IllegalArgumentException {
    public static final String NOT_NUMBER_FORMAT = "숫자를 입력해주세요.";
    public static final String NULL_OR_EMPTY = "Null 혹은 빈 문자열이 입력되었습니다.";

    public InvalidMoneyException(String s) {
        super(s);
    }
}
