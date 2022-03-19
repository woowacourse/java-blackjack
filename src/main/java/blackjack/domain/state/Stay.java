package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finished {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State state) {
        Cards otherCards = state.cards();

        if (otherCards.isBust() || cards.totalScore() > otherCards.totalScore()) {
            return 1;
        }

        if (cards.totalScore() == otherCards.totalScore()) {
            return 0;
        }

        return -1;
    }
}
