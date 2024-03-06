package blackjack.domain;

public class Player {
    private final Name name;
    private final Hands hands;

    public Player(Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    void addCard(Card card) {
        hands.addCard(card);
    }
}
