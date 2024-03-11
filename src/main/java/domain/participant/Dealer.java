package domain.participant;

import domain.blackjack.Deck;
import domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_HIT_COUNT = 16;

    private final Deck deck;

    public Dealer() {
        super(new Name("딜러"));
        deck = new Deck();
    }

    public boolean shouldHit() {
        return hands.calculateScore() <= DEALER_HIT_COUNT;
    }

    public Card draw() {
        return deck.draw();
    }
}
