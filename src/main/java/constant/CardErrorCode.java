package constant;

public enum CardErrorCode implements ErrorCode {
    NO_CARD_IN_DECK("CARD_001", "카드 뭉치에 더 이상 남아있는 카드가 없습니다."),
    DUPLICATED_CARD_IN_DECK("CARD_002", "덱에 중복된 카드가 있습니다.");

    private final String code;
    private final String message;

    CardErrorCode(String code, String message) {
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

