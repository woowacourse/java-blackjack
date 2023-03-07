package blackjack.domain.player;

public class Dealer extends User {

    private static final int MINIMUM_SCORE = 17;

    public boolean hasUnderMinimumScore() {
        return hand.getTotalScore() < MINIMUM_SCORE;
    }
}
