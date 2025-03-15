package blackjack;

public enum GameRule {

    BLACKJACK_POINT(21, "블랙잭 점수"),
    BUST_THRESHOLD_POINT(21, "버스트 경곗값"),
    INITIAL_DEALING_CARD_COUNT(2, "초기 카드 배분 개수"),
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

    public static boolean isBlackjack(int cardCount, int sumOfCards) {
        return cardCount == INITIAL_DEALING_CARD_COUNT.value && sumOfCards == BLACKJACK_POINT.value;
    }

    public static int applyBustRule(int sumOfCards) {
        if (isBust(sumOfCards)) {
            return 0;
        }
        return sumOfCards;
    }

    public int getValue() {
        return value;
    }
}
