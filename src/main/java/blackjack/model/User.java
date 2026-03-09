package blackjack.model;

import java.util.List;

public class User {

    private final String name;
    private final Hand hand;

    public User(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }
}

