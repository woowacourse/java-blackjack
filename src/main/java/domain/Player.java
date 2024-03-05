package domain;

public class Player {

    private static final int BLACK_JACK = 21;

    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public boolean isBurst() {
        return getTotalScore() > BLACK_JACK;
    }

    public int getTotalScore() {
        return hand.calculateSum();
    }

    public Hand getHand() {
        return hand;
    }
}
