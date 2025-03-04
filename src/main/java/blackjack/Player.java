package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void receiveHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getTotal() {
        int total = 0;
        for (Card card : hand) {
            CardValue cardValue = card.getCardValue();
            total += cardValue.getDefaultValue();
        }
        if (total <= 11 && hasAce()) {
            total += 10;
        }
        return total;
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(card -> card.getCardValue() == CardValue.ACE);
    }
}
