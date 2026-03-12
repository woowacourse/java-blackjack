package blackjack.model;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public Dealer(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public boolean canHit() {
        return getScore().value() <= HIT_THRESHOLD;
    }
}
