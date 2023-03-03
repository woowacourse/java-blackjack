package player;

import java.util.List;

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

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }
}
