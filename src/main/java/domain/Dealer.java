
package domain;

public class Dealer extends Gamer {
    private static final int HIT_CONDITION = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Decks decks) {
        super(decks);
        this.name = new Name(DEALER_NAME);
    }

    @Override
    public void hit(final Decks decks) {
        while (hand.sum() < HIT_CONDITION) {
            hand.add(decks.draw());
        }
    }
}
