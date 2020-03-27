package blackjack.domain.rule;

public class BasicRule {
    public static final int BLACK_JACK_SCORE = 21;
    public static final int BLACK_JACK_CARD_COUNT = 2;
    public static final double BLACK_JACK_PROFIT = 1.5;

    public static boolean isBusted(int score) {
        return score > BLACK_JACK_SCORE;
    }

    public static boolean isBlackJack(int score, int cardCount) {
        return score == BLACK_JACK_SCORE && cardCount == BLACK_JACK_CARD_COUNT;
    }
}
