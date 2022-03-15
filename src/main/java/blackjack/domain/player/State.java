package blackjack.domain.player;

import blackjack.domain.Rule;
import blackjack.domain.card.Cards;

public enum State {

    BLACKJACK,
    BUST,
    NONE,
    ;

    private static final int BLACKJACK_SIZE = 2;

    public static State from(final Cards cards) {
        if (cards.calculateTotalScore() > Rule.WINNING_SCORE.getValue()) {
            return BUST;
        }
        if (cards.getSize() == BLACKJACK_SIZE && cards.calculateTotalScore() == Rule.WINNING_SCORE.getValue()) {
            return BLACKJACK;
        }
        return NONE;
    }
}
