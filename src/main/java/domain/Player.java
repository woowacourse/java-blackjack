package domain;

import java.util.List;

public class Player {
    private final String name;
    private final Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public List<String> getHandToString() {
        return hand.toStringList();
    }

    public int getScore(){
        return hand.calculateScore();
    }
}