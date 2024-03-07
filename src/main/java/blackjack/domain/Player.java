package blackjack.domain;

import java.util.List;

public class Player {
    private final Name name;
    private final Hands hands;

    public Player(String name) {
        this.name = new Name(name);
        this.hands = new Hands();
    }

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }
}
