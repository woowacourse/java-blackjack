package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Dealer extends Participant {
    private final Deck deck;

    public Dealer(List<Card> hand, Deck deck) {
        super(hand);
        this.deck = deck;
    }

    public Card dealCard() {
        return deck.draw();
    }

    public boolean canReceiveCard() {
        return calculateScore() <= 16;
    }
}
