
package domain;

public class Dealer {
    private static final int HIT_CONDITION = 17;
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void hit(final Decks decks) {
        while (hand.sum() < HIT_CONDITION) {
            hand.add(decks.draw());
        }
    }

    public int calculateTotalScore() {
        return hand.sum();
    }
}
