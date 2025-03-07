package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;

public class Dealer extends Participant {

    public Dealer() {
        super(new CardDeck());
    }

    public Card getOpenCard() {
        return cardDeck.getCards().getFirst();
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= 16);
    }
}
