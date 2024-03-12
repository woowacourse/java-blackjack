package domain.gamer;

import domain.card.Card;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";
    private static final int STAY_CONDITION = 17;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public Card openFirstCard() {
        return getCardsInHand().get(0);
    }

    @Override
    public boolean isOverTurn() {
        return getHand().sum() >= STAY_CONDITION;
    }
}
