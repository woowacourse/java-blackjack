package blackjack.domain.rule;

public class BasicRule {
    public static final int BLACK_JACK_SCORE = 21;

    public static boolean isBusted(int score) {
        return score > BLACK_JACK_SCORE;
    }
}
