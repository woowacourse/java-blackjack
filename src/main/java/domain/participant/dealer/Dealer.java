package domain.participant.dealer;

import static game.BlackjackGame.BLACKJACK_SCORE;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Participant;
import domain.participant.attributes.Name;
import domain.participant.player.Players;

public class Dealer extends Participant {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int DEALER_HIT_THRESHOLD = 16;

    private static final Name DEFAULT_DEALER_NAME = new Name("딜러");

    private final Cards deck;
    private boolean hasSoftAce;

    public Dealer(final Cards cards) {
        super(DEFAULT_DEALER_NAME);
        deck = cards;
    }

    public void shuffleCards() {
        deck.shuffle();
    }

    public void dealInitialCards(final Players players) {
        hasSoftAce = false;
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
        if (this == participant) {
            setHasSoftAce();
        }
    }

    private void setHasSoftAce() {
        if (!hasSoftAce) {
            hasSoftAce = hand.hasAce() && (hand.score(true) <= BLACKJACK_SCORE);
        }
    }

    public Card peek() {
        return hand.peek();
    }

    public boolean canHit() {
        return score() <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public int score() {
        return hand.score(hasSoftAce);
    }
}
