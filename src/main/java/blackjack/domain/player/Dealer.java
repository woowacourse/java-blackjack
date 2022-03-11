package blackjack.domain.player;

import blackjack.domain.card.Cards;


public class Dealer extends Participant{

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    public boolean doesNeedToDraw() {
        return super.getScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }
}
