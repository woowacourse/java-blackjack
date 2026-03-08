package domain;

import java.util.List;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean canReceiveCard() {
        return hand.canHit();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<String> showHand() {
        return hand.showHand();
    }
}
