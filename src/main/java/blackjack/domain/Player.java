package blackjack.domain;

public class Player extends Participant {

    public static final double BLACKJACK_WINNING_PROFIT_RATE = 1.5;

    private final BettingAmount bettingAmount;

    public Player(String name, int bettingAmount) {
        this(new Name(name), new BettingAmount(bettingAmount));
    }

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public Score compete(Participant dealer) {
        if (dealer.isBust()) {
            return getScoreWithBust();
        }
        if (this.isBust()) {
            return Score.LOSE;
        }
        if (this.isBlackjack()) {
            return getScoreWithBlackjack(dealer);
        }
        return Score.compare(this.getTotalNumber(), dealer.getTotalNumber());
    }

    private Score getScoreWithBust() {
        if (this.isBust()) {
            return Score.LOSE;
        }
        return Score.WIN;
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }

    public double getTotalProfit(Score myScore) {
        if (myScore == Score.WIN && isBlackjack()) {
            return bettingAmount.getAmount() * BLACKJACK_WINNING_PROFIT_RATE;
        }
        return (double) bettingAmount.getAmount() * myScore.getProfitRate();
    }
}
