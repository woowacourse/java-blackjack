package view;

public enum OutputMessage {
    DISTRIBUTE("딜러와 %s에게 2장을 나누었습니다."),
    DEALER_DRAW("딜러는 16이하라 한장의 카드를 더 받았습니다."),
    PARTICIPANTS_RESULT("%s - 결과: %d"),

    RESULT_HEADER("## 최종 승패"),
    DEALER_WINNING_CONDITION("딜러: %d승 %d패"),
    PLAYER_WINNING_CONDITION("%s: %s"),

    NAME_AND_CARDS("%s카드: %s"),
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
