package player;

import card.Card;

public class Player {
    private final Name name;
    private final Hand hand = new Hand();

    public Player(Name name) {
        this.name = name;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }
}
