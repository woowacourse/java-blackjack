package team.blackjack.domain.rule;

public class DefaultBlackjackRule {
    public static final int BLACKJACK = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int DEALER_STAND_SCORE = 17;

    public boolean isBust(int score) {
        return score > BLACKJACK;
    }

    public boolean isBlackjack(int score, int cardCount) {
        if (cardCount != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return score == BLACKJACK;
    }

    public boolean isDealerMustDraw(int score) {
        return score < DEALER_STAND_SCORE;
    }
}
