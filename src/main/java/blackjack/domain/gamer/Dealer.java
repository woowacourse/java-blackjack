package blackjack.domain.gamer;

public class Dealer extends BlackjackGamer {

    private static final String DEFAULT_DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(new Name(DEFAULT_DEALER_NAME));
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() <= DEALER_DRAW_THRESHOLD;
    }
}
