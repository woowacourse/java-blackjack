package blackjack.model;

import java.util.List;

public class Player {

    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name.get();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }
}
