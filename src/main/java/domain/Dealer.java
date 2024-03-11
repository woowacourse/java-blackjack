package domain;

public class Dealer extends Gamer{
    private static final int DEALER_HIT_CONDITION = 16;
    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public int hit(Deck deck) {
        int hitCount = 0;
        while (this.getTotalScore() <= DEALER_HIT_CONDITION) {
            hand.add(deck.draw());
            hitCount++;
        }
        return hitCount;
    }
}
