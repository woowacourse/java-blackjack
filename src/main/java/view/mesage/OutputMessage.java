package view.mesage;

public enum OutputMessage {
    DEAL_INITIAL_CARDS("딜러와 %s에게 2장을 나누었습니다."),
    DELIMITER(", "),
    PARTICIPANT_CARDS("%s카드: %s"),
    DEALER_DRAW_CARD("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    RESULT_TEXT("%s카드 %s - 결과: %d"),
    FINAL_MESSAGE("## 최종 승패"),
    DEALER_RESULT_FORMAT("딜러: %d"),
    PLAYER_RESULT_FORMAT("%s: %d"),
    DEALER("딜러"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
