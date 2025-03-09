package domain.participant;

import domain.card.Card;

public class Dealer extends AbstractGambler {

    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getOpenCard() {
        return cardDeck.getCards().getFirst();
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= 16);
    }
}
