package domain.game;

import domain.card.Card;
import java.util.List;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void drawCard(List<Card> cards) {
        hand.drawCard(cards);
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalWithAce();
    }

    public boolean isOverBustBound() {
        return hand.isOverBustBound();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
