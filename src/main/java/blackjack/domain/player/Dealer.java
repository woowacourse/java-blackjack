package blackjack.domain.player;

public class Dealer extends User {

    private static final int SCORE_LIMIT = 17;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean isUnderLimit() {
        return TotalScore.calculateTotalScore(hand.getHand()).getTotalScore() < SCORE_LIMIT;
    }
}
