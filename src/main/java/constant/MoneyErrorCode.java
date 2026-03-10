package constant;

public enum MoneyErrorCode implements ErrorCode{
    MONEY_IS_NEGATIVE("MONEY_001", "돈은 음수일 수 없습니다."),
    MONEY_IS_ZERO("MONEY_002", "0원 이상을 입력해주세요."),
    MONEY_IS_NOT_NUMBER("MONEY_003", "문자가 아닌 숫자를 입력해주세요."),
    MONEY_IS_OUT_OF_RANGE("MONEY_004", "입력 가능한 범위를 초과한 숫자입니다.");

    private final String code;
    private final String message;

    MoneyErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
