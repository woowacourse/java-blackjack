package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Dealer implements Playable {
    private static final int HITTABLE_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    private final Name name;
    private Hand hand;

    public Dealer(final Hand hand) {
        this.name = new Name(DEALER_NAME);
        this.hand = hand;
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

    public boolean canHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public Name getName() {
        return name;
    }

    public List<Card> openCard() {
        return List.of(hand.getFirstCard());
    }
}
