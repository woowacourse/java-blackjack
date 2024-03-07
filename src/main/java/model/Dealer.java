package model;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return cardDeck.calculateHand() <= HIT_THRESHOLD;
    }
}
