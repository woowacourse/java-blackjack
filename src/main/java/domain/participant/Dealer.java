package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import static domain.BlackjackGame.DEALER_HIT_THRESHOLD;

public class Dealer extends Participant {

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Cards deck;

    public Dealer(final Cards cards) {
        super(DEFAULT_DEALER_NAME);
        deck = cards;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffleCards() {
        deck.shuffle();
    }

    public void deal(final Participant participant) {
        participant.receive(deck.draw());
    }

    @Override
    public boolean canReceiveMoreCard() {
        return score() <= DEALER_HIT_THRESHOLD;
    }
}
