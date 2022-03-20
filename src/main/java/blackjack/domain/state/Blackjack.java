package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public class Blackjack extends Finished {

    private static final double BLACKJACK_RATE = 1.5;
    private static final double DRAW_RATE = 1;

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackMatch match(State state) {
        final Cards opponentCards = state.getCards();
        if (opponentCards.isBlackjack()) {
            return BlackjackMatch.DRAW;
        }
        return BlackjackMatch.WIN;
    }

    @Override
    public double profitRate(BlackjackMatch blackjackMatch) {
        if (blackjackMatch == BlackjackMatch.WIN) {
            return BLACKJACK_RATE;
        }
        return DRAW_RATE;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
