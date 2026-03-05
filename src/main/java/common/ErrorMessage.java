package common;

public enum ErrorMessage {
    HIT_OR_STAND_VALUE_MIS_MATCH("y 혹은 n만 입력 가능합니다."),
    DRAW_CARD_OUT_OF_RANGE("양수 이상의 숫자 중 남은 카드 수 만큼만 선택 가능");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
