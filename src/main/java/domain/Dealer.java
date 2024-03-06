
package domain;

public class Dealer extends Gamer {
    private static final int HIT_CONDITION = 17;

    public Dealer() {
    }

    @Override
    public void hit(final Decks decks) {
        while (hand.sum() < HIT_CONDITION) {
            hand.add(decks.draw());
        }
    }
}
