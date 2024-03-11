package blackjack.domain.gamer;

public class Player extends Gamer {

    private static final int HIT_THRESHOLD = 21;

    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return hand.sum() <= HIT_THRESHOLD;
    }

    public Name getName() {
        return name;
    }
}
