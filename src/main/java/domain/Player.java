package domain;

import java.util.List;

public class Player {
    private final String name;
    private final Hand hand;

    private Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(String name, Hand hand) {
        return new Player(name, hand);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}
