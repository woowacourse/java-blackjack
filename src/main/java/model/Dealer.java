package model;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean canHit() {
        return cardDeck.calculateHand() <= HIT_THRESHOLD;
    }
}
