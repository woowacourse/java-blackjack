package blackjack.domain.player;

import blackjack.domain.card.Cards;


public class Dealer extends Participant{

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;
    public static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(Cards cards) {
        super(DEALER_DEFAULT_NAME, cards);
    }

    public boolean doesNeedToDraw() {
        return super.getScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }
}
