package domain.participant.dealer;

import static domain.card.Hand.INITIAL_CARD_COUNT;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.attributes.Name;
import domain.participant.player.Players;

public class Dealer extends Participant {
    public static final int DEALER_HIT_THRESHOLD = 16;

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Deck deck;

    public Dealer(final Deck deck) {
        super(DEFAULT_DEALER_NAME);
        this.deck = deck;
    }

    public void shuffleCards() {
        deck.shuffle();
    }

    public void dealInitialCards(final Players players) {
        deal(this, INITIAL_CARD_COUNT);
        players.forEach(participant -> deal(participant, INITIAL_CARD_COUNT));
    }

    public void deal(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            deal(participant);
        }
    }

    public void deal(final Participant participant) {
        participant.receive(deck.draw());
    }

    public Card peek() {
        return hand().peek();
    }

    @Override
    public boolean canHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }
}
