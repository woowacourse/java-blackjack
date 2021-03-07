package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int LIMIT_SCORE = 17;

    public Dealer() {
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore() >= LIMIT_SCORE;
    }
}
