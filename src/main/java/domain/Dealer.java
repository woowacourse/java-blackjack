package domain;

import domain.card.Card;

public class Dealer extends Participant {
    private static final int HIT_LIMIT = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < HIT_LIMIT;
    }

    public Score calculateScore() {
        return new Score(hand.getSum(), isBlackJack());
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }
}
