package blackjack.domain;

public class Player {

    private final String name;
    protected final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void initializeHand(Card card1, Card card2) {
        hand.put(card1);
        hand.put(card2);
    }

    public int calculate() {
        return hand.calculate();
    }

    public void putCard(Card card) {
        hand.put(card);
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public boolean canHit() {
        return hand.calculate() <= 21;
    }
}
