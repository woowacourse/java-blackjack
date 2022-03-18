package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
