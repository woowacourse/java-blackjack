package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
