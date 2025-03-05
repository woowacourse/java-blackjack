package blackjack.common;

public enum ErrorMessage {
    INVALID_CARD_VALUE("카드 타입에 맞지 않는 값입니다."),
    INVALID_DECK_SIZE("카드 개수는 52개여야 합니다."),
    DUPLICATED_CARD_EXISTED("중복된 카드가 존재합니다."),
    EMPTY_DECK_SIZE("모든 카드를 소진하였습니다."),
    CAN_NOT_NULL("null일 수 는 없습니다.");

    private final String message;


    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    }
