package blackjack.domain.gamer;

public class Player extends Gamer {

    private static final int HIT_THRESHOLD = 21;

    private final Name name;
    private final BetAmount betAmount;

    public Player(final String name, final long betAmount) {
        this.name = new Name(name);
        this.betAmount = new BetAmount(betAmount);
    }

    @Override
    public boolean canHit() {
        return hand.sum() <= HIT_THRESHOLD;
    }

    public Name getName() {
        return name;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }
}
