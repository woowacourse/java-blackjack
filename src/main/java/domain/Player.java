package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Hand hand = new Hand();
    private boolean isBust = false;

    public Player(String name) {
        this.name = name;
    }

    public boolean isBust() {
        return isBust;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand.getHand();
    }

    public List<String> getHandToString() {
        return hand.toStringList();
    }

    public void setBust() {
        isBust = true;
    }
}