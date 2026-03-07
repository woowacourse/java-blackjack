package domain;

import java.util.List;

public class Player {
    private final Name name;
    private final Hand hand;

    private Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(Name name, Hand hand) {
        return new Player(name, hand);
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public Hand getHand() {
        return hand;
    }

    public void addHandCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.getScore().isBust();
    }
}
