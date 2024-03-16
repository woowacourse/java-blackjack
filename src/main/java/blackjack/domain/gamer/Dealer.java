package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends BlackjackGamer {

    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean canReceiveCard() {
        return getValue() <= DEALER_DRAW_THRESHOLD;
    }

    public Card getFirstCard() {
        return getHand().getFirstCard();
    }
}
