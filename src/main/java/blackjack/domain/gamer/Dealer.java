package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {
    private static final String NAME = "딜러";
    private static final int DEALER_STAND_CONDITION = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void addCard(Card card) {
        if (getScore() > DEALER_STAND_CONDITION) {
            return;
        }

        super.addCard(card);
    }

    @Override
    public boolean isNotBust() {
        return getScore() <= DEALER_STAND_CONDITION;
    }
}
