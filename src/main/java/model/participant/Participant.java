package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Deck;
import model.state.CardHand;

public abstract class Participant {
    protected final CardHand cardHand;

    public Participant(final Deck deck) {
        this.cardHand = CardHand.drawInitialHand(deck);
    }

    public abstract List<Card> openInitialCard();

    public abstract Name getName();
}
