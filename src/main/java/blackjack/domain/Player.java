package blackjack.domain;

public class Player {
    private final Name name;
    private final Hands hands;

    public Player(Name name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    void addCard(Card card) {
        hands.addCard(card);
    }
}
