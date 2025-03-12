package domain.game;

import domain.card.Card;
import java.util.List;

public class Dealer {

    private static final int DEALER_DRAW_BOUND = 16;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void drawCard(List<Card> cards) {
        hand.drawCard(cards);
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalWithAce();
    }

    public boolean isOverBustBound() {
        return hand.isOverBustBound();
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public Card getSingleCard() {
        return hand.getCards().getFirst();
    }
}
