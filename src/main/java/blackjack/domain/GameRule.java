package blackjack.domain;

public enum GameRule {

    BUST_THRESHOLD_POINT(21, "버스트 경곗값"),
    INITIAL_DEALING_CARD_COUNT(2, "초기 카드 배분 개수"),

    BUSTED_POINT(0, "버스트 경곗값 초과 -> 최소 점수(0)"),
    ;

    private final int value;
    private final String description;

    GameRule(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static boolean isBust(int sumOfCards) {
        return sumOfCards > GameRule.BUST_THRESHOLD_POINT.getValue();
    }

    public static int applyBustRule(int sumOfCards) {
        if (isBust(sumOfCards)) {
            return BUSTED_POINT.value;
        }
        return sumOfCards;
    }

    public int getValue() {
        return value;
    }
}
