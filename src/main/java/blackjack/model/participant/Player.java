package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Player {
    private static final int HITTABLE_THRESHOLD = 21;

    protected final Name name;
    protected Hand hand;

    Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(final String rawName, final Hand hand) {
        return new Player(new Name(rawName), hand);
    }

    public void receiveCard(final Card card) {
        this.hand = hand.addCard(card);
    }

    public List<Card> openCards() {
        return hand.getCards();
    }

    public boolean canHit() {
        return hand.calculateScore() < HITTABLE_THRESHOLD;
    }

    public boolean isBust() {
        return hand.calculateScore() > HITTABLE_THRESHOLD;
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public int getCardCount() {
        return hand.countSize();
    }

    public Name getName() {
        return name;
    }
}
