package domain.participant;

public class Player extends Participant {

    public static final int MINIMUM_BET_AMOUNT = 0;
    public static final int MAXIMUM_BET_AMOUNT = 1_000_000;

    private final int betAmount = 0;

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        return !isBust() && !isBlackjack();
    }
}
