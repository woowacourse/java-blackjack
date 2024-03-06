package blackjack.domain;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public int calculate() {
        return hand.calculate();
    }

    public void putCard(Card card) {
        hand.put(card);
    }
}
