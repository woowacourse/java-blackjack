package domain;

import domain.card.Cards;

public class Dealer extends Player {

    private static final int MIN_SCORE_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(final Cards cards) {
        super(DEALER_NAME, cards);
    }

    public Dealer()  {
        super(DEALER_NAME, Cards.empty());
    }

    @Override
    public boolean canReceiveCard() {
        return super.getScore().getValue() <= MIN_SCORE_THRESHOLD;
    }
}
