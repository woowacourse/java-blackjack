package blackjack.domain.player;

public class Dealer extends AbstractPlayer implements Player {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int MAX_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isValidRange() {
        return getScore() <= MAX_SCORE;
    }
}
