package blackjack.domain;

public class Player extends Participant {

    private final BettingAmount bettingAmount;

    public Player(String name, int bettingAmount) {
        this(new Name(name), new BettingAmount(bettingAmount));
    }

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    @Override
    public Score compete(Participant player) {
        if (player.isBust()) {
            return getScoreWithBust();
        }

        if (this.isBust()) {
            return Score.LOSE;
        }

        return Score.compare(this.getTotalNumber(), player.getTotalNumber());
    }

    private Score getScoreWithBust() {
        if (this.isBust()) {
            return Score.LOSE;
        }
        return Score.WIN;
    }

    public double getTotalProfit(Score myScore) {
        if (myScore == Score.LOSE) {
            return (double) bettingAmount.getAmount() * -1;
        }
        if (myScore == Score.DRAW) {
            return 0;
        }

        if (cards.isBlackjack()) {
            return bettingAmount.getAmount() * 1.5;
        }
        return bettingAmount.getAmount();
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }
}
