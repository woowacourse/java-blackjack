package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Deck;

public final class Dealer extends Participant {
    public Dealer(final Deck deck) {
        super(deck);
    }

    @Override
    public List<Card> openInitialCard() {
        return List.of(cardHand.getFirstCard());
    }

    @Override
    public Name getName() {
        return new Name("딜러");
    }
}
