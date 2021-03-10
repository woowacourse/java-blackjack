package blackjack.domain.gamer;

public class Dealer extends Participants {

    private static final int DEALER_MAX_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canDraw() {
        return this.getTakenCards().calculateScore() <= DEALER_MAX_SCORE;
    }
}

