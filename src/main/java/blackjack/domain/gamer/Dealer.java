package blackjack.domain.gamer;

public class Dealer extends BlackjackGamer {

    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() <= DEALER_DRAW_THRESHOLD;
    }
}
