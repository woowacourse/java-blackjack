package blackjack.domain.player;

public class Dealer extends User {

    private static final int MINIMUM_SCORE = 17;

    @Override
    public boolean isUnderLimit() {
        return playerCards.getTotalScore() < MINIMUM_SCORE;
    }

}
