package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer {
    public static final int HIT_BOUNDARY = 16;
    private static final String NAME = "딜러";

    private final Name name;
    private final Hand hand;

    private Dealer(Hand hand) {
        this.name = new Name(NAME);
        this.hand = hand;
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    public boolean needsToHit() {
        return hand.calculateTotalScore() <= HIT_BOUNDARY;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> openFirstCard() {
        return List.of(hand.getCards().getFirst());
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public int totalScore() {
        return hand.calculateTotalScore();
    }

    public String name() {
        return name.getName();
    }
}