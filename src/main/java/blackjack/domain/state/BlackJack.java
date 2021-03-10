package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackJack extends Finished {
    public static final int BLACKJACK_NUMBER = 21;

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
