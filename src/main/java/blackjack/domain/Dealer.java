package blackjack.domain;

public class Dealer {
    private final Name name;
    private final Hands hands;

    public Dealer(Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    void addCard(Card card) {
        hands.addCard(card);
    }
}
