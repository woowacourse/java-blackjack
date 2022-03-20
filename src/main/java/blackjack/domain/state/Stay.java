package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.result.BlackjackMatch;

public class Stay extends Finished {

    private static final double WIN_RATE = 1;
    private static final double DRAW_RATE = 0;
    private static final double LOSE_RATE = -1;

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackMatch showMatch(Status status) {
        final Cards opponentCards = status.getCards();
        if (opponentCards.isBust() || cards.calculateScore() > opponentCards.calculateScore()) {
            return BlackjackMatch.WIN;
        }
        if (cards.calculateScore() == opponentCards.calculateScore()) {
            return BlackjackMatch.DRAW;
        }
        return BlackjackMatch.LOSE;
    }

    @Override
    public double profitRate(BlackjackMatch blackjackMatch) {
        if (blackjackMatch == BlackjackMatch.WIN) {
            return WIN_RATE;
        }
        if (blackjackMatch == BlackjackMatch.DRAW) {
            return DRAW_RATE;
        }
        return LOSE_RATE;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
