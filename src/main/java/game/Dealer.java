package game;

import card.Card;
import java.util.List;

public class Dealer {

    private static final int DEALER_DRAW_BOUND = 16;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void draw(List<Card> cards) {
        hand.drawCard(cards);
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalPoints() {
        return hand.calculate();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isOverDrawBound() {
        return calculateTotalPoints() > DEALER_DRAW_BOUND;
    }

    public Card getSingleCard() {
        return hand.getCards().getFirst();
    }


    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
    
}
