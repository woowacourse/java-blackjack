package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String NAME = "딜러";
    private static final int BOUNDARY = 16;

    private final Name name;
    private final Hand hand;

    public Dealer(Hand hand) {
        this.name = new Name(NAME);
        this.hand = hand;
    }

    public boolean needsToHit() {
        return hand.getTotalScore() <= BOUNDARY;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addHand(Card card) {
        hand.add(card);
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public int getBoundary() {
        return BOUNDARY;
    }

    public String getName() {
        return name.getName();
    }
}
