package domain.participant;

import domain.card.Card;
import domain.card.HandCards;

public class Dealer extends Participant {

    public static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super(new HandCards());
    }

    public Card getOpenCard() {
        return handCards.getCards().getFirst();
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= DEALER_HIT_THRESHOLD);
    }
}
