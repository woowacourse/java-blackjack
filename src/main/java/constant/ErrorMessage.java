package constant;

public enum ErrorMessage {

    INVALID_INPUT_PATTERN("입력 형식이 잘못됐습니다."),
    EXCEEDED_MAX_TRY("최대 횟수를 초과 하였습니다."),
    INVALID_CARD_RANK_CODE("존재하지 않은 카드 랭크 코드입니다."),
    INVALID_CARD_SHAPE_CODE("존재하지 않은 카드 모양 코드입니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
