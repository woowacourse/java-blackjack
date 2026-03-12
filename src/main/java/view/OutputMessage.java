package view;

public enum OutputMessage {
    DIVIDE("딜러와 %s에게 2장을 나누었습니다."),
    DEALER_DRAW("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    PARTICIPANTS_RESULT("%s - 결과: %d"),

    RESULT_PROFIT_HEADER("## 최종 수익"),
    DEALER_PROFIT("딜러: %d"),
    PLAYER_PROFIT("%s: %d"),

    GAME_LOG("%s카드: %s"),
    ;

    private final String description;

    OutputMessage(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public String description(Object... args) {
        return String.format(description, args);
    }
}
