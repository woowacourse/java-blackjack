package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Player implements Playable {
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

    @Override
    public void receiveCard(final Card card) {
        this.hand = hand.addCard(card);
    }

    @Override
    public List<Card> openCards() {
        return hand.getCards();
    }

    @Override
    public int getScore() {
        return hand.calculateScore();
    }

    @Override
    public int getCardCount() {
        return hand.countSize();
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() < HITTABLE_THRESHOLD;
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    public Name getName() {
        return name;
    }
}
