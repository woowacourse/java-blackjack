package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NICKNAME = "딜러";
    private static final int DEALER_SCORE = 16;

    public Dealer() {
        super(DEALER_NICKNAME);
    }

    @Override
    public boolean isDrawable() {
        return hand.getTotalScore() <= DEALER_SCORE;
    }
}
