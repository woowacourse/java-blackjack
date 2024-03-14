package blackjack.domain.gamer;

public class Player extends Gamer {

    private static final int HIT_THRESHOLD = 21;

    private final Name name;
    private final BetAmount betAmount;

    private Player(Name name, BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public static Player of(String name, long betAmount) {
        return new Player(new Name(name), new BetAmount(betAmount));
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
