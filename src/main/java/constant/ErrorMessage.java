package constant;

public enum ErrorMessage {

    INVALID_INPUT_PATTERN("입력 형식이 잘못됐습니다."),
    EXCEEDED_MAX_TRY("최대 횟수를 초과 하였습니다."),
    INVALID_CARD_RANK_CODE("존재하지 않은 카드 랭크 코드입니다."),
    INVALID_CARD_SHAPE_CODE("존재하지 않은 카드 모양 코드입니다."),
    INVALID_BETTING_VALUE("유효하지 않은 배팅값입니다."),
    PLAYER_BETTING_NOT_FOUND("해당 플레이어의 배팅이 존재하지 않습니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
