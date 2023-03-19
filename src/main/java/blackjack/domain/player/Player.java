package blackjack.domain.player;

public class Player extends User {

    private static final int SCORE_LIMIT = 21;
    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    @Override
    public boolean isUnderLimit() {
        return TotalScore.calculateTotalScore(hand.getHand()).getTotalScore() < SCORE_LIMIT;
    }
}
