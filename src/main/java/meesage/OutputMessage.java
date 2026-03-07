package meesage;

import java.util.List;

public enum OutputMessage {
    DEAL_INITIAL_CARDS("딜러와 %s에게 2장을 나누었습니다."),
    DELIMITER(", "),
    PLAYER_CARD_INFO("%s카드: %s"),
    DEALER_CARD_INFO("딜러카드: %s"),
    DEALER_DRAW_CARD("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    RESULT_TEXT("%s - 결과: %d"),
    FINAL_MESSAGE("## 최종 승패"),
    DEALER_RESULT_FORMAT("딜러: %d승 %d무 %d패"),
    PLAYER_RESULT_FORMAT("%s : %s")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String join(List<String> input) {
        return String.join(message, input);
    }

    public String format(Object... args) {return String.format(message, args);}

    public String getMessage() {
        return message;
    }
}
