package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {

    protected Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State state) {
        Cards otherCards = state.cards();
        if (otherCards.isBlackjack()) {
            return 0;
        }

        return 1.5;
    }
}
