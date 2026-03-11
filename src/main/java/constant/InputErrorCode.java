package constant;

public enum InputErrorCode implements ErrorCode {
    INPUT_IS_BLANK("INPUT_001", "빈 값을 입력하셨습니다."),
    INVALID_CONDITION_INPUT("INPUT_002", "유효한 형식의 입력으로 넣어주세요(y 또는 n)");

    private final String code;
    private final String message;

    InputErrorCode(String code, String message) {
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

