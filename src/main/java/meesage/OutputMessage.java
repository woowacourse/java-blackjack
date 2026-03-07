package meesage;

import java.util.List;

public enum OutputMessage {
    DEAL_INITIAL_CARDS("딜러와 %s에게 2장을 나누었습니다."),
    DELIMITER(", "),
    CARD_TEXT("카드: "),
    DEALER_DRAW_CARD("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    RESULT_TEXT(" - 결과: "),
    FINAL_MESSAGE("## 최종 승패"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String format(List<String> input) {
        return String.join(DELIMITER.getMessage(), input);
    }
}
